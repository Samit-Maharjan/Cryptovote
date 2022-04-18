package com.example.cryptovote;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cryptovote.contracts.Election;
import org.web3j.protocol.Web3j;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.math.BigDecimal;

public class Blockchain {
    String PRIVATE_KEY = "";
    String CONTRACT_ADDRESS = "";

    private final Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:7545") );
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    private Credentials getCredentialsFromPrivateKey(){
        return Credentials.create(PRIVATE_KEY);
    }

    private String deployContract(Web3j web3j, Credentials credentials) throws Exception{
        return Election.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT, PRIVATE_KEY)
                .send()
                .getContractAddress();
    }

    private Election loadContract(String CONTRACT_ADDRESS, Web3j web3j, Credentials credentials){
        return Election.load(CONTRACT_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    private void AddVoter(Election election, String name, String address) throws Exception{
        election.addVoter(name, address);
    }

    private void AddCandidate(Election election, String name, String address) throws Exception{
        election.addCandidate(name, address);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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
