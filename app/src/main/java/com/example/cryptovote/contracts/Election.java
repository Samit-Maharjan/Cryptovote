package com.example.cryptovote.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.StaticArray2;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.4.
 */
@SuppressWarnings("rawtypes")
public class Election extends Contract {
    private static final String BINARY = "60806040526000600555600060065534801561001a57600080fd5b5061227c8061002a6000396000f3fe608060405234801561001057600080fd5b506004361061010b5760003560e01c80638f47b146116100a2578063e6a5e61d11610071578063e6a5e61d146102ba578063eb40afc0146102d6578063ee2574af146102f2578063f4ab9adf14610322578063f851a4401461033e5761010b565b80638f47b1461461021d57806396dfcbea1461024e578063a9a981a31461026c578063e13804a71461028a5761010b565b80634a2a84c5116100de5780634a2a84c5146101965780636f6233a3146101b2578063704b6c02146101d057806379614b2e146101ec5761010b565b806302d947ef1461011057806307e4b70a1461012c57806309eef43e1461014857806342169e4814610178575b600080fd5b61012a600480360381019061012591906118ab565b61035c565b005b610146600480360381019061014191906118eb565b610724565b005b610162600480360381019061015d91906118eb565b61085a565b60405161016f9190611933565b60405180910390f35b6101806108a8565b60405161018d919061195d565b60405180910390f35b6101b060048036038101906101ab9190611abe565b6108ae565b005b6101ba610a76565b6040516101c79190611933565b60405180910390f35b6101ea60048036038101906101e591906118eb565b610ac4565b005b61020660048036038101906102019190611b1a565b610be6565b604051610214929190611b47565b60405180910390f35b610237600480360381019061023291906118ab565b610c5a565b604051610245929190611b47565b60405180910390f35b610256610d70565b6040516102639190611bf8565b60405180910390f35b610274610ee0565b604051610281919061195d565b60405180910390f35b6102a4600480360381019061029f9190611b1a565b610ee6565b6040516102b19190611bf8565b60405180910390f35b6102d460048036038101906102cf91906118eb565b610fe1565b005b6102f060048036038101906102eb9190611c1a565b611154565b005b61030c600480360381019061030791906118eb565b6112f7565b6040516103199190611933565b60405180910390f35b61033c600480360381019061033791906118eb565b611350565b005b61034661173a565b6040516103539190611c69565b60405180910390f35b600160028111156103705761036f611c84565b5b600460149054906101000a900460ff16600281111561039257610391611c84565b5b146103d2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103c990611d25565b60405180910390fd5b80600160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160019054906101000a900460ff1615610463576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161045a90611d91565b60405180910390fd5b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020154116104e8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104df90611dfd565b60405180910390fd5b600160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16610577576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161056e90611e69565b60405180910390fd5b8260008111801561058a57506005548111155b6105c9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105c090611ed5565b60405180910390fd5b60018060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160016101000a81548160ff02191690831515021790555083600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010181905550600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600201546003600086815260200190815260200160002060008282546106cf9190611f24565b925050819055506000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002018190555050505050565b80600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16146107b5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107ac90611fec565b60405180910390fd5b600160028111156107c9576107c8611c84565b5b600460149054906101000a900460ff1660028111156107eb576107ea611c84565b5b1461082b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161082290611d25565b60405180910390fd5b6002600460146101000a81548160ff0219169083600281111561085157610850611c84565b5b02179055505050565b600080600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020154149050919050565b60065481565b80600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161461093f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161093690611fec565b60405180910390fd5b6000600281111561095357610952611c84565b5b600460149054906101000a900460ff16600281111561097557610974611c84565b5b14806109b3575060028081111561098f5761098e611c84565b5b600460149054906101000a900460ff1660028111156109b1576109b0611c84565b5b145b6109f2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109e990612058565b60405180910390fd5b60056000815480929190610a0590612078565b919050555060055460026000600554815260200190815260200160002060000181905550826002600060055481526020019081526020016000206001019080519060200190610a55929190611760565b50600060036000600554815260200190815260200160002081905550505050565b600060016002811115610a8c57610a8b611c84565b5b600460149054906101000a900460ff166002811115610aae57610aad611c84565b5b14610abc5760009050610ac1565b600190505b90565b60006002811115610ad857610ad7611c84565b5b600460149054906101000a900460ff166002811115610afa57610af9611c84565b5b1480610b385750600280811115610b1457610b13611c84565b5b600460149054906101000a900460ff166002811115610b3657610b35611c84565b5b145b610b77576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b6e90612058565b60405180910390fd5b80600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600460146101000a81548160ff02191690836002811115610bde57610bdd611c84565b5b021790555050565b60008082600081118015610bfc57506005548111155b610c3b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610c3290611ed5565b60405180910390fd5b8360036000868152602001908152602001600020549250925050915091565b60008082600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614610cee576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ce590611fec565b60405180910390fd5b846001600080600089815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002015492509250509250929050565b606060006002811115610d8657610d85611c84565b5b600460149054906101000a900460ff166002811115610da857610da7611c84565b5b1415610deb576040518060400160405280601681526020017f456c656374696f6e204e6f7420537461727465642121000000000000000000008152509050610edd565b60016002811115610dff57610dfe611c84565b5b600460149054906101000a900460ff166002811115610e2157610e20611c84565b5b1415610e64576040518060400160405280601281526020017f456c656374696f6e2053746172746564212100000000000000000000000000008152509050610edd565b600280811115610e7757610e76611c84565b5b600460149054906101000a900460ff166002811115610e9957610e98611c84565b5b1415610edc576040518060400160405280601081526020017f456c656374696f6e20456e6465642121000000000000000000000000000000008152509050610edd565b5b90565b60055481565b606081600081118015610efb57506005548111155b610f3a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610f3190611ed5565b60405180910390fd5b600260008481526020019081526020016000206001018054610f5b906120f0565b80601f0160208091040260200160405190810160405280929190818152602001828054610f87906120f0565b8015610fd45780601f10610fa957610100808354040283529160200191610fd4565b820191906000526020600020905b815481529060010190602001808311610fb757829003601f168201915b5050505050915050919050565b80600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614611072576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161106990611fec565b60405180910390fd5b6000600281111561108657611085611c84565b5b600460149054906101000a900460ff1660028111156110a8576110a7611c84565b5b14806110e657506002808111156110c2576110c1611c84565b5b600460149054906101000a900460ff1660028111156110e4576110e3611c84565b5b145b611125576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161111c90612058565b60405180910390fd5b6001600460146101000a81548160ff0219169083600281111561114b5761114a611c84565b5b02179055505050565b80600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16146111e5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016111dc90611fec565b60405180910390fd5b600060028111156111f9576111f8611c84565b5b600460149054906101000a900460ff16600281111561121b5761121a611c84565b5b1480611259575060028081111561123557611234611c84565b5b600460149054906101000a900460ff16600281111561125757611256611c84565b5b145b611298576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161128f90612058565b60405180910390fd5b60018060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548160ff021916908315150217905550505050565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff169050919050565b80600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156113e2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016113d990612194565b60405180910390fd5b600060028111156113f6576113f5611c84565b5b600460149054906101000a900460ff16600281111561141857611417611c84565b5b1480611456575060028081111561143257611431611c84565b5b600460149054906101000a900460ff16600281111561145457611453611c84565b5b145b611495576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161148c90612058565b60405180910390fd5b81600160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160019054906101000a900460ff1615801561153557506000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020154145b801561158e5750600160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16155b6115cd576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016115c490612226565b60405180910390fd5b600660008154809291906115e090612078565b919050555082600080600654815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060018060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600201819055506000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548160ff0219169083151502179055506000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160016101000a81548160ff021916908315150217905550505050565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b82805461176c906120f0565b90600052602060002090601f01602090048101928261178e57600085556117d5565b82601f106117a757805160ff19168380011785556117d5565b828001600101855582156117d5579182015b828111156117d45782518255916020019190600101906117b9565b5b5090506117e291906117e6565b5090565b5b808211156117ff5760008160009055506001016117e7565b5090565b6000604051905090565b600080fd5b600080fd5b6000819050919050565b61182a81611817565b811461183557600080fd5b50565b60008135905061184781611821565b92915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006118788261184d565b9050919050565b6118888161186d565b811461189357600080fd5b50565b6000813590506118a58161187f565b92915050565b600080604083850312156118c2576118c161180d565b5b60006118d085828601611838565b92505060206118e185828601611896565b9150509250929050565b6000602082840312156119015761190061180d565b5b600061190f84828501611896565b91505092915050565b60008115159050919050565b61192d81611918565b82525050565b60006020820190506119486000830184611924565b92915050565b61195781611817565b82525050565b6000602082019050611972600083018461194e565b92915050565b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6119cb82611982565b810181811067ffffffffffffffff821117156119ea576119e9611993565b5b80604052505050565b60006119fd611803565b9050611a0982826119c2565b919050565b600067ffffffffffffffff821115611a2957611a28611993565b5b611a3282611982565b9050602081019050919050565b82818337600083830152505050565b6000611a61611a5c84611a0e565b6119f3565b905082815260208101848484011115611a7d57611a7c61197d565b5b611a88848285611a3f565b509392505050565b600082601f830112611aa557611aa4611978565b5b8135611ab5848260208601611a4e565b91505092915050565b60008060408385031215611ad557611ad461180d565b5b600083013567ffffffffffffffff811115611af357611af2611812565b5b611aff85828601611a90565b9250506020611b1085828601611896565b9150509250929050565b600060208284031215611b3057611b2f61180d565b5b6000611b3e84828501611838565b91505092915050565b6000604082019050611b5c600083018561194e565b611b69602083018461194e565b9392505050565b600081519050919050565b600082825260208201905092915050565b60005b83811015611baa578082015181840152602081019050611b8f565b83811115611bb9576000848401525b50505050565b6000611bca82611b70565b611bd48185611b7b565b9350611be4818560208601611b8c565b611bed81611982565b840191505092915050565b60006020820190508181036000830152611c128184611bbf565b905092915050565b60008060408385031215611c3157611c3061180d565b5b6000611c3f85828601611896565b9250506020611c5085828601611896565b9150509250929050565b611c638161186d565b82525050565b6000602082019050611c7e6000830184611c5a565b92915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f54686520656c656374696f6e20686173206e6f742073746172746564206f722060008201527f616c726561647920656e6465642e000000000000000000000000000000000000602082015250565b6000611d0f602e83611b7b565b9150611d1a82611cb3565b604082019050919050565b60006020820190508181036000830152611d3e81611d02565b9050919050565b7f54686520566f7465722068617320616c726561647920766f7465642e00000000600082015250565b6000611d7b601c83611b7b565b9150611d8682611d45565b602082019050919050565b60006020820190508181036000830152611daa81611d6e565b9050919050565b7f54686520566f74657220686173206e6f7420766f746564207965742e00000000600082015250565b6000611de7601c83611b7b565b9150611df282611db1565b602082019050919050565b60006020820190508181036000830152611e1681611dda565b9050919050565b7f54686520766f746572206973206e6f7420726567697374657265642e00000000600082015250565b6000611e53601c83611b7b565b9150611e5e82611e1d565b602082019050919050565b60006020820190508181036000830152611e8281611e46565b9050919050565b7f496e76616c69642043616e6469646174652e0000000000000000000000000000600082015250565b6000611ebf601283611b7b565b9150611eca82611e89565b602082019050919050565b60006020820190508181036000830152611eee81611eb2565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000611f2f82611817565b9150611f3a83611817565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115611f6f57611f6e611ef5565b5b828201905092915050565b7f4f6e6c7920656c656374696f6e2061646d696e2063616e20616363657373206660008201527f756e6374696f6e21000000000000000000000000000000000000000000000000602082015250565b6000611fd6602883611b7b565b9150611fe182611f7a565b604082019050919050565b6000602082019050818103600083015261200581611fc9565b9050919050565b7f54686520656c656374696f6e2068617320616c72656164792073746172746564600082015250565b6000612042602083611b7b565b915061204d8261200c565b602082019050919050565b6000602082019050818103600083015261207181612035565b9050919050565b600061208382611817565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8214156120b6576120b5611ef5565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061210857607f821691505b6020821081141561211c5761211b6120c1565b5b50919050565b7f54686520656c656374696f6e2061646d696e2063616e6e6f742061636365737360008201527f20746869732066756e6374696f6e000000000000000000000000000000000000602082015250565b600061217e602e83611b7b565b915061218982612122565b604082019050919050565b600060208201905081810360008301526121ad81612171565b9050919050565b7f566f7465722068617320616c7265616479206265656e2072656769737465726560008201527f642e000000000000000000000000000000000000000000000000000000000000602082015250565b6000612210602283611b7b565b915061221b826121b4565b604082019050919050565b6000602082019050818103600083015261223f81612203565b905091905056fea26469706673582212203234876b72a63a0f06e74309db1bce2bc6c7c5496e8e0bdb0c3b81de14391b2564736f6c634300080c0033"; 

    public static final String FUNC_ADDCANDIDATE = "addCandidate";

    public static final String FUNC_ADDVOTER = "addVoter";

    public static final String FUNC_ADMIN = "admin";

    public static final String FUNC_CANDIDATECOUNT = "candidateCount";

    public static final String FUNC_CHECKELECTIONSTART = "checkElectionStart";

    public static final String FUNC_CHECKREGISTERED = "checkRegistered";

    public static final String FUNC_CHECKSTATE = "checkState";

    public static final String FUNC_ENDELECTION = "endElection";

    public static final String FUNC_GETCANDIDATENAME = "getCandidateName";

    public static final String FUNC_GETVOTER = "getVoter";

    public static final String FUNC_HASVOTED = "hasVoted";

    public static final String FUNC_SETADMIN = "setAdmin";

    public static final String FUNC_SHOWRESULTS = "showResults";

    public static final String FUNC_STARTELECTION = "startElection";

    public static final String FUNC_VERIFYVOTER = "verifyVoter";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_VOTERCOUNT = "voterCount";

    @Deprecated
    protected Election(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Election(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> addCandidate(String _name, String owner) {
        final Function function = new Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addVoter(String _voter) {
        final Function function = new Function(
                FUNC_ADDVOTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voter)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> admin() {
        final Function function = new Function(
                FUNC_ADMIN, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> candidateCount() {
        final Function function = new Function(
                FUNC_CANDIDATECOUNT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> checkElectionStart() {
        final Function function = new Function(
                FUNC_CHECKELECTIONSTART, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference <Bool>(){} ));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> checkRegistered(String _voter) {
        final Function function = new Function(
                FUNC_CHECKREGISTERED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voter)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
       return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<String> checkState() {
        final Function function = new Function(
                FUNC_CHECKSTATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(){}));
       return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> endElection(String owner) {
        final Function function = new Function(
                FUNC_ENDELECTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getCandidateName(BigInteger _ID) {
        final Function function = new Function(
                FUNC_GETCANDIDATENAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(){} ));
       return executeRemoteCallSingleValueReturn(function, String.class);
    }


    public RemoteCall<List> getVoter(BigInteger ID, String owner) {
        final Function function = new Function(
                FUNC_GETVOTER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ID),
                        new org.web3j.abi.datatypes.Address(160, owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray2<Uint256>>() { }));
        return new RemoteCall<List>(
                new Callable<List>(){
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception{
                        List<Type> result = executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                }
        );
    }

    public RemoteCall<Boolean> hasVoted(String _voter) {
        final Function function = new Function(
                FUNC_HASVOTED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voter)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>(){}));
       return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> setAdmin(String owner) {
        final Function function = new Function(
                FUNC_SETADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }


    public RemoteCall<List> showResults(BigInteger _ID) {
        final Function function = new Function(
                FUNC_SHOWRESULTS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray2<Uint256>>() { }));
        return new RemoteCall<List>(
                new Callable<List>(){
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception{
                        List<Type> result = executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                }
        );
    }

    public RemoteCall<TransactionReceipt> startElection(String owner) {
        final Function function = new Function(
                FUNC_STARTELECTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> verifyVoter(String _voter, String owner) {
        final Function function = new Function(
                FUNC_VERIFYVOTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voter), 
                new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> vote(BigInteger _ID, String owner) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ID), 
                new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> voterCount() {
        final Function function = new Function(
                FUNC_VOTERCOUNT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Election load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Election(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Election(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Election.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Election.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}


