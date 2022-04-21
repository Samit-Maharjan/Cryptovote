package com.example.cryptovote;
import android.os.StrictMode;

import com.example.cryptovote.contracts.Election;

import org.web3j.protocol.Web3j;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    String PRIVATE_KEY = "db564546b3c40b6b4ca3e563dede6b1ea5bfbc8ed8c77d907bd9c66fe493716f";
    String CONTRACT_ADDRESS = "0xb12c6fd0b59da920fd3ae3c1241d953b116fab6d";

    private final static int adminID = 0;
    private String adminAddress;
    private Election election;
    private EthAccounts ethAccounts;

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    public static void main(String args[]) throws Exception{
        Blockchain blockchain = new Blockchain();
    }

    Blockchain(){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));

            Web3j web3j = Web3j.build(new HttpService("http://172.17.12.248:7545"));

            Credentials credentials = getCredentialsFromPrivateKey();

            String addresses = deployContract(web3j, credentials);
            System.out.println(addresses);

            ethAccounts = web3j.ethAccounts().sendAsync().get();

            election = loadContract(CONTRACT_ADDRESS, web3j, credentials);

            adminAddress = ethAccounts.getAccounts().get(adminID);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private Credentials getCredentialsFromPrivateKey(){
        return Credentials.create(PRIVATE_KEY);
    }

    private String deployContract(Web3j web3j, Credentials credentials) throws Exception{
        return Election.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT)
                .send()
                .getContractAddress();
    }

    private Election loadContract(String CONTRACT_ADDRESS, Web3j web3j, Credentials credentials){
        return Election.load(CONTRACT_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    public String getAddress(int userID){
        return ethAccounts.getAccounts().get(userID + 1);
    }

    public void AddVoter(int ID) throws Exception {
        election.addVoter(getAddress(ID) ).sendAsync().get();
    }

    public void AddCandidate(String name) throws Exception {
        election.addCandidate(name, adminAddress).sendAsync().get();
    }

    public int GetCandidate(int ID) throws Exception{
        List results = election.showResults(BigInteger.valueOf(ID) ).sendAsync().get();
        return ( (BigInteger) results.get(1) ).intValue();
    }

    public String GetCandidateName(int ID) throws Exception{
        return election.getCandidateName(BigInteger.valueOf(ID) ).sendAsync().get();
    }

    public String findState() throws Exception {
        return election.checkState().sendAsync().get();
    }

    public boolean CheckElectionStart() throws Exception{
        return election.checkElectionStart().sendAsync().get();
    }
    public boolean CheckRegistered(int ID) throws Exception{
        return election.checkRegistered(getAddress(ID)).sendAsync().get();
    }
    public boolean checkVoted(int ID) throws Exception{
        return (voterInfo(ID) == 0 );
    }

    public void VerifyVoter(int ID) throws Exception{
        election.verifyVoter(getAddress(ID), adminAddress).sendAsync().get();
    }

    public void initAdmin() throws Exception {
        election.setAdmin(adminAddress).sendAsync().get();
    }

    public void startElection() throws Exception {
        election.startElection(adminAddress).sendAsync().get();
    }

    public void endElection() throws Exception {
        election.endElection(adminAddress).sendAsync().get();
    }

    public void voteCandidate(int candidateID, int userID) throws Exception {
        election.vote(BigInteger.valueOf(candidateID), getAddress(userID) ).sendAsync().get();
    }

    public int voterInfo(int userID) throws Exception{
        List results = (List) election.getVoter(BigInteger.valueOf(userID + 1), adminAddress).sendAsync().get();
        return ((BigInteger)results.get(1)).intValue();
    }

    public String getState() throws Exception{
        return election.checkState().sendAsync().get();
    }

    public int countCandidates() throws Exception{
        return election.returnCandidateCount().sendAsync().get().intValue();
    }

    public int countVoters() throws Exception{
        return election.returnVoterCount().sendAsync().get().intValue();
    }

    public void setVoterWeight(int ID) throws Exception{
        election.giveVoterWeight(getAddress(ID)).sendAsync().get();
    }

    private void transferEth(Web3j web3j, Credentials credentials, String RECIPIENT) throws Exception{
        TransactionManager transactionManager = new RawTransactionManager(
                web3j,
                credentials
        );
        Transfer transfer = new Transfer(web3j, transactionManager);
        TransactionReceipt transactionReceipt = transfer.sendFunds(
                RECIPIENT,
                BigDecimal.ONE,
                Convert.Unit.ETHER,
                GAS_PRICE,
                GAS_LIMIT
        ).send();
    }
}
