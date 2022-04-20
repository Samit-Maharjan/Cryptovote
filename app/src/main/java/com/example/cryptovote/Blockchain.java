package com.example.cryptovote;

import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.RequiresApi;
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
    String PRIVATE_KEY = "24aa2125d95f386066ce651d660a00b96c3e95f979445b1937ca255c6d4215a6";
    String CONTRACT_ADDRESS = "0x19bf69051bbcf274b767883f2420533a3cf7cdf3";

    private final static int adminID = 0;
    private String adminAddress;
    private Election election;
    private EthAccounts ethAccounts;

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    public static void main(String args[]) throws Exception {
        Blockchain blockchain = new Blockchain();
        //int votes = blockchain.GetCandidate(0);
        String name = blockchain.GetCandidateName(1);
        System.out.println("Name: " + name);
       // System.out.println("Votes: " + votes);
    }
    Blockchain(){
        try {
 //           StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
///            StrictMode.setThreadPolicy(policy);

            //Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));

            Web3j web3j = Web3j.build(new HttpService("http://172.17.12.248:7545"));

            Credentials credentials = getCredentialsFromPrivateKey();

            //String addresses = deployContract(web3j, credentials);
            //System.out.println(addresses);

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
        election.addVoter(ethAccounts.getAccounts().get(ID + 1) ).send();
    }

    public void AddCandidate(String name) throws Exception {
        election.addCandidate(name, adminAddress).send();
    }

    public int GetCandidate(int ID) throws Exception{
        List results = election.showResults(BigInteger.valueOf(ID) ).send();
        return (int) results.get(1);
    }

    public String GetCandidateName(int ID) throws Exception{
        return election.getCandidateName(BigInteger.valueOf(ID) ).send();
    }

    public String findState() throws Exception {
        return election.checkState().send();
    }

    public boolean CheckElectionStart() throws Exception{
        return election.checkElectionStart().send();
    }
    public boolean CheckRegistered(int ID) throws Exception{
        return election.checkRegistered(getAddress(ID)).send();
    }
    public boolean checkVoted(String address) throws Exception{
        return election.hasVoted(address).send();
    }

    public void VerifyVoter(int ID) throws Exception{
        election.verifyVoter(getAddress(ID +1), adminAddress);
    }

    public void initAdmin() throws Exception {
        election.setAdmin(adminAddress).send();
    }

    public void startElection() throws Exception {
        election.startElection(adminAddress).send();
    }

    public void endElection() throws Exception {
        election.endElection(adminAddress).send();
    }

    public void voteCandidate(BigInteger candidateID, int userID) throws Exception {
        election.vote(candidateID, ethAccounts.getAccounts().get(userID) ).send();
    }

    public int voterInfo(BigInteger userID) throws Exception{
        List results = (List) election.getVoter(userID, adminAddress);
        return (int) results.get(1);
    }

    public String getState() throws Exception{
        return election.checkState().send();
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
