package com.example.cryptovote;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

public class Blockchain {
    Blockchain(int index, int String){
        try{
            Web3j web3 = Web3j.build(new HttpService("http://127.0.0.1:7545"));
            Web3ClientVersion clientVersion = web3.web3ClientVersion().send();
            System.out.println("Client Version: " + clientVersion.getWeb3ClientVersion() );

            EthGasPrice gasPrice = web3.ethGasPrice().send();
            System.out.println("Default Gas Price: " + gasPrice.getGasPrice());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
