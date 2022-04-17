package com.example.cryptovote;

import android.annotation.SuppressLint;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Optional;


public class Blockchain {
    Credentials credentials;
    Web3ClientVersion web3ClientVersion;
    EthSendTransaction ethSendTransaction;
    Optional<TransactionReceipt> transactionReceipt;
    String privateKey="1993504787fb8fc36ee0c70cb6ec64f268110f68bb0648518252946a1f9a1b3b";
    String recipientAddress="0x1633fcA3DE5499Ecf0d0756C1be70435003d5Df1";
    final Web3j web3 = Web3j.build(new HttpService("http://127.0.0.1:7545") );

    @SuppressLint("NewApi")
    public Blockchain(int index, String UserID) {
        try {
            web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
            credentials = Credentials.create(privateKey);
            EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            BigInteger value = Convert.toWei("1", Convert.Unit.ETHER).toBigInteger();

            BigInteger gasLimit = BigInteger.valueOf(6721975);
            BigInteger gasPrice = Convert.toWei("1", Convert.Unit.GWEI).toBigInteger();

            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, recipientAddress, value);
            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);

            ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();
            String transactionHash = ethSendTransaction.getTransactionHash();
            System.out.println("Transaction Hash: " + transactionHash);

            do {
                EthGetTransactionReceipt ethGetTransactionReceipt = web3.ethGetTransactionReceipt(transactionHash).send();
                transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt();
            } while (!transactionReceipt.isPresent());

            System.out.println("Transaction " + transactionHash +  "in the Block #" + transactionReceipt.get().getBlockNumber() );
            System.out.println("Balance: " + Convert.fromWei(web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
            .send().getBalance().toString(), Convert.Unit.ETHER));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
