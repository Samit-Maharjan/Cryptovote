package com.example.cryptovote.contracts;
/*
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
/*
public class Election extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060405161154438038061154483398101604081905261002f91610054565b600480546001600160a81b0319166001600160a01b0392909216919091179055610084565b60006020828403121561006657600080fd5b81516001600160a01b038116811461007d57600080fd5b9392505050565b6114b1806100936000396000f3fe608060405234801561001057600080fd5b50600436106100ea5760003560e01c806396dfcbea1161008c578063e445b81711610066578063e445b817146101f4578063e6a5e61d14610217578063f851a4401461022a578063fa106d401461025557600080fd5b806396dfcbea146101bf578063a9a981a3146101d4578063b7e03264146101dd57600080fd5b80634a2a84c5116100c85780634a2a84c51461013357806379614b2e146101465780638dc83771146101685780638f47b1461461018957600080fd5b806302d947ef146100ef57806307e4b70a1461010457806342169e4814610117575b600080fd5b6101026100fd3660046110de565b610268565b005b61010261011236600461110a565b610521565b61012060065481565b6040519081526020015b60405180910390f35b610102610141366004611142565b6105ab565b6101596101543660046111fb565b6106ab565b60405161012a93929190611261565b61017b6101763660046111fb565b61082e565b60405161012a92919061128a565b61019c6101973660046110de565b6108de565b604080519384526001600160a01b0390921660208401529082015260600161012a565b6101c7610949565b60405161012a91906112ab565b61012060055481565b6101e5610a9f565b60405161012a939291906112be565b61020761020236600461110a565b610c0a565b60405161012a94939291906112e3565b61010261022536600461110a565b610d32565b60045461023d906001600160a01b031681565b6040516001600160a01b03909116815260200161012a565b610102610263366004611312565b610de9565b6002600454600160a01b900460ff1660038111156102885761028861133c565b146102da5760405162461bcd60e51b815260206004820152601c60248201527f54686520656c656374696f6e206973206e6f74206f6e676f696e672e0000000060448201526064015b60405180910390fd5b6001600160a01b0381166000908152600160205260409020548190610100900460ff161561034a5760405162461bcd60e51b815260206004820152601c60248201527f54686520566f7465722068617320616c726561647920766f7465642e0000000060448201526064016102d1565b6001600160a01b0381166000908152600160205260409020600201546103b25760405162461bcd60e51b815260206004820152601c60248201527f54686520566f74657220686173206e6f7420766f746564207965742e0000000060448201526064016102d1565b6001600160a01b03811660009081526001602052604090205460ff1661041a5760405162461bcd60e51b815260206004820152601c60248201527f54686520766f746572206973206e6f7420726567697374657265642e0000000060448201526064016102d1565b8260008111801561042d57506005548111155b61046e5760405162461bcd60e51b815260206004820152601260248201527124b73b30b634b21021b0b73234b230ba329760711b60448201526064016102d1565b6001600160a01b0383166000908152600160208181526040808420805461ff00191661010017815592830188905560029092015487845260039091529082208054919290916104be908490611368565b90915550506001600160a01b03831660009081526001602052604080822060020191909155517fb769d0e3866fdc4ca4186927d759d56b3dce3b77f88cf6d21493074c9170c39e906105139086815260200190565b60405180910390a150505050565b60045481906001600160a01b038083169116146105505760405162461bcd60e51b81526004016102d190611380565b6004805460ff60a01b1916600360a01b17908190556040517ff5655ac31a580dd1a7d73c106aa6db1364ec50203ae4ef7ee56230ce3a95df0a9161059f91600160a01b90910460ff16906113c8565b60405180910390a15050565b60045481906001600160a01b038083169116146105da5760405162461bcd60e51b81526004016102d190611380565b6000600454600160a01b900460ff1660038111156105fa576105fa61133c565b146106175760405162461bcd60e51b81526004016102d1906113f0565b6005805490600061062783611425565b9091555050600554600081815260026020908152604090912091825584516106559260010191860190611029565b506005805460009081526003602052604080822091909155905490517f65f3c06ee06fb9d991bec47c7ce27a1b63c03df9b4e93c3d68dfe55cbf8d08439161069e91869061128a565b60405180910390a1505050565b60006060816003600454600160a01b900460ff1660038111156106d0576106d061133c565b1461071d5760405162461bcd60e51b815260206004820152601f60248201527f54686520656c656374696f6e20686173206e6f7420656e646564207965742e0060448201526064016102d1565b8360008111801561073057506005548111155b6107715760405162461bcd60e51b815260206004820152601260248201527124b73b30b634b21021b0b73234b230ba329760711b60448201526064016102d1565b60008581526002602090815260408083206003909252909120546001909101805487929082906107a090611440565b80601f01602080910402602001604051908101604052809291908181526020018280546107cc90611440565b80156108195780601f106107ee57610100808354040283529160200191610819565b820191906000526020600020905b8154815290600101906020018083116107fc57829003601f168201915b50505050509150935093509350509193909250565b60008181526002602052604081208054600190910180546060929190819061085590611440565b80601f016020809104026020016040519081016040528092919081815260200182805461088190611440565b80156108ce5780601f106108a3576101008083540402835291602001916108ce565b820191906000526020600020905b8154815290600101906020018083116108b157829003601f168201915b5050505050905091509150915091565b6004546000908190819084906001600160a01b038083169116146109145760405162461bcd60e51b81526004016102d190611380565b505050600083815260208181526040808320546001600160a01b03168084526001909252909120600201549394909392509050565b60606000600454600160a01b900460ff16600381111561096b5761096b61133c565b14156109a25750604080518082019091526016815275456c656374696f6e204e6f742053746172746564212160501b602082015290565b6001600454600160a01b900460ff1660038111156109c2576109c261133c565b14156109f55750604080518082019091526012815271456c656374696f6e2053746172746564212160701b602082015290565b6002600454600160a01b900460ff166003811115610a1557610a1561133c565b1415610a4b5750604080518082019091526015815274456c656374696f6e20696e2050726f63657373212160581b602082015290565b6003600454600160a01b900460ff166003811115610a6b57610a6b61133c565b1415610a9c575060408051808201909152601081526f456c656374696f6e20456e646564212160801b602082015290565b90565b60606000806003600454600160a01b900460ff166003811115610ac457610ac461133c565b14610b115760405162461bcd60e51b815260206004820152601f60248201527f54686520656c656374696f6e20686173206e6f7420656e646564207965742e0060448201526064016102d1565b600080606060015b6005548111610bfe57600081815260036020526040902054841015610bec5760008181526002602052604090206001018054610b5490611440565b80601f0160208091040260200160405190810160405280929190818152602001828054610b8090611440565b8015610bcd5780601f10610ba257610100808354040283529160200191610bcd565b820191906000526020600020905b815481529060010190602001808311610bb057829003601f168201915b5050506000848152600360205260409020549650929450909250839150505b80610bf681611425565b915050610b19565b50945092509050909192565b60008080606060015b6006548111610d29576000818152602081905260409020546001600160a01b0387811691161415610d1757600081815260208181526040808320546001600160a01b03168352600180835281842060028082015491830154808752945291909320909201805484939291908190610c8990611440565b80601f0160208091040260200160405190810160405280929190818152602001828054610cb590611440565b8015610d025780601f10610cd757610100808354040283529160200191610d02565b820191906000526020600020905b815481529060010190602001808311610ce557829003601f168201915b50505050509050945094509450945050610d2b565b80610d2181611425565b915050610c13565b505b9193509193565b60045481906001600160a01b03808316911614610d615760405162461bcd60e51b81526004016102d190611380565b6000600454600160a01b900460ff166003811115610d8157610d8161133c565b14610d9e5760405162461bcd60e51b81526004016102d1906113f0565b6004805460ff60a01b1916600160a01b908117918290556040517f8c007cb1a003f4d620158719e2c5ac3328770d1b61b3642f6408925ddd1c23039261059f92900460ff16906113c8565b60045481906001600160a01b03808316911614610e185760405162461bcd60e51b81526004016102d190611380565b60045483906001600160a01b0380831691161415610e8f5760405162461bcd60e51b815260206004820152602e60248201527f54686520656c656374696f6e2061646d696e2063616e6e6f742061636365737360448201526d103a3434b990333ab731ba34b7b760911b60648201526084016102d1565b6000600454600160a01b900460ff166003811115610eaf57610eaf61133c565b14610ecc5760405162461bcd60e51b81526004016102d1906113f0565b6001600160a01b0384166000908152600160205260409020548490610100900460ff16158015610f1557506001600160a01b038116600090815260016020526040902060020154155b8015610f3a57506001600160a01b03811660009081526001602052604090205460ff16155b610f915760405162461bcd60e51b815260206004820152602260248201527f566f7465722068617320616c7265616479206265656e20726567697374657265604482015261321760f11b60648201526084016102d1565b60068054906000610fa183611425565b909155505060065460009081526020818152604080832080546001600160a01b0319166001600160a01b038a1690811790915580845260018084529382902060028101859055805460ff1916909417909355519182527fa59a52faad62c47c19bc6374ac361f8a69733ddd96817ceb432bb2b0d70d4f00910160405180910390a15050505050565b82805461103590611440565b90600052602060002090601f016020900481019282611057576000855561109d565b82601f1061107057805160ff191683800117855561109d565b8280016001018555821561109d579182015b8281111561109d578251825591602001919060010190611082565b506110a99291506110ad565b5090565b5b808211156110a957600081556001016110ae565b80356001600160a01b03811681146110d957600080fd5b919050565b600080604083850312156110f157600080fd5b82359150611101602084016110c2565b90509250929050565b60006020828403121561111c57600080fd5b611125826110c2565b9392505050565b634e487b7160e01b600052604160045260246000fd5b6000806040838503121561115557600080fd5b823567ffffffffffffffff8082111561116d57600080fd5b818501915085601f83011261118157600080fd5b8135818111156111935761119361112c565b604051601f8201601f19908116603f011681019083821181831017156111bb576111bb61112c565b816040528281528860208487010111156111d457600080fd5b826020860160208301376000602084830101528096505050505050611101602084016110c2565b60006020828403121561120d57600080fd5b5035919050565b6000815180845260005b8181101561123a5760208185018101518683018201520161121e565b8181111561124c576000602083870101525b50601f01601f19169290920160200192915050565b83815260606020820152600061127a6060830185611214565b9050826040830152949350505050565b8281526040602082015260006112a36040830184611214565b949350505050565b6020815260006111256020830184611214565b6060815260006112d16060830186611214565b60208301949094525060400152919050565b8481528360208201528260408201526080606082015260006113086080830184611214565b9695505050505050565b6000806040838503121561132557600080fd5b61132e836110c2565b9150611101602084016110c2565b634e487b7160e01b600052602160045260246000fd5b634e487b7160e01b600052601160045260246000fd5b6000821982111561137b5761137b611352565b500190565b60208082526028908201527f4f6e6c7920656c656374696f6e2061646d696e2063616e206163636573732066604082015267756e6374696f6e2160c01b606082015260800190565b60208101600483106113ea57634e487b7160e01b600052602160045260246000fd5b91905290565b6020808252818101527f54686520656c656374696f6e2068617320616c72656164792073746172746564604082015260600190565b600060001982141561143957611439611352565b5060010190565b600181811c9082168061145457607f821691505b6020821081141561147557634e487b7160e01b600052602260045260246000fd5b5091905056fea26469706673582212202188e1eab7eec22e8526f082d27586168aa130918d7c6e10968e92e996a63cf864736f6c634300080c0033";

    public static final String FUNC_ADDCANDIDATE = "addCandidate";

    public static final String FUNC_ADDVOTER = "addVoter";

    public static final String FUNC_ADMIN = "admin";

    public static final String FUNC_CANDIDATECOUNT = "candidateCount";

    public static final String FUNC_CHECKSTATE = "checkState";

    public static final String FUNC_DISPLAYCANDIDATE = "displayCandidate";

    public static final String FUNC_ENDELECTION = "endElection";

    public static final String FUNC_GETVOTER = "getVoter";

    public static final String FUNC_SHOWRESULTS = "showResults";

    public static final String FUNC_SHOWWINNER = "showWinner";

    public static final String FUNC_STARTELECTION = "startElection";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_VOTERCOUNT = "voterCount";

    public static final String FUNC_VOTERPROFILE = "voterProfile";

    public static final Event ADDEDACANDIDATE_EVENT = new Event("addedACandidate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event ADDEDAVOTER_EVENT = new Event("addedAVoter", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event ELECTIONEND_EVENT = new Event("electionEnd", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
    ;

    public static final Event ELECTIONSTART_EVENT = new Event("electionStart", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
    ;

    public static final Event VOTEDSUCCESSFULLY_EVENT = new Event("votedSuccessfully", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

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

    public List<AddedACandidateEventResponse> getAddedACandidateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDEDACANDIDATE_EVENT, transactionReceipt);
        ArrayList<AddedACandidateEventResponse> responses = new ArrayList<AddedACandidateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddedACandidateEventResponse typedResponse = new AddedACandidateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.candidateID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.candidateName = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddedACandidateEventResponse> addedACandidateEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AddedACandidateEventResponse>() {
            @Override
            public AddedACandidateEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDEDACANDIDATE_EVENT, log);
                AddedACandidateEventResponse typedResponse = new AddedACandidateEventResponse();
                typedResponse.log = log;
                typedResponse.candidateID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.candidateName = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddedACandidateEventResponse> addedACandidateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDEDACANDIDATE_EVENT));
        return addedACandidateEventFlowable(filter);
    }

    public List<AddedAVoterEventResponse> getAddedAVoterEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDEDAVOTER_EVENT, transactionReceipt);
        ArrayList<AddedAVoterEventResponse> responses = new ArrayList<AddedAVoterEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddedAVoterEventResponse typedResponse = new AddedAVoterEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voter = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddedAVoterEventResponse> addedAVoterEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AddedAVoterEventResponse>() {
            @Override
            public AddedAVoterEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDEDAVOTER_EVENT, log);
                AddedAVoterEventResponse typedResponse = new AddedAVoterEventResponse();
                typedResponse.log = log;
                typedResponse.voter = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddedAVoterEventResponse> addedAVoterEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDEDAVOTER_EVENT));
        return addedAVoterEventFlowable(filter);
    }

    public List<ElectionEndEventResponse> getElectionEndEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ELECTIONEND_EVENT, transactionReceipt);
        ArrayList<ElectionEndEventResponse> responses = new ArrayList<ElectionEndEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ElectionEndEventResponse typedResponse = new ElectionEndEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.election_state = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ElectionEndEventResponse> electionEndEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ElectionEndEventResponse>() {
            @Override
            public ElectionEndEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ELECTIONEND_EVENT, log);
                ElectionEndEventResponse typedResponse = new ElectionEndEventResponse();
                typedResponse.log = log;
                typedResponse.election_state = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ElectionEndEventResponse> electionEndEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ELECTIONEND_EVENT));
        return electionEndEventFlowable(filter);
    }

    public List<ElectionStartEventResponse> getElectionStartEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ELECTIONSTART_EVENT, transactionReceipt);
        ArrayList<ElectionStartEventResponse> responses = new ArrayList<ElectionStartEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ElectionStartEventResponse typedResponse = new ElectionStartEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.election_state = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ElectionStartEventResponse> electionStartEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ElectionStartEventResponse>() {
            @Override
            public ElectionStartEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ELECTIONSTART_EVENT, log);
                ElectionStartEventResponse typedResponse = new ElectionStartEventResponse();
                typedResponse.log = log;
                typedResponse.election_state = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ElectionStartEventResponse> electionStartEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ELECTIONSTART_EVENT));
        return electionStartEventFlowable(filter);
    }

    public List<VotedSuccessfullyEventResponse> getVotedSuccessfullyEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTEDSUCCESSFULLY_EVENT, transactionReceipt);
        ArrayList<VotedSuccessfullyEventResponse> responses = new ArrayList<VotedSuccessfullyEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotedSuccessfullyEventResponse typedResponse = new VotedSuccessfullyEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.candidateId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VotedSuccessfullyEventResponse> votedSuccessfullyEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VotedSuccessfullyEventResponse>() {
            @Override
            public VotedSuccessfullyEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTEDSUCCESSFULLY_EVENT, log);
                VotedSuccessfullyEventResponse typedResponse = new VotedSuccessfullyEventResponse();
                typedResponse.log = log;
                typedResponse.candidateId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VotedSuccessfullyEventResponse> votedSuccessfullyEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTEDSUCCESSFULLY_EVENT));
        return votedSuccessfullyEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addCandidate(String _name, String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addVoter(String _voter, String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDVOTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voter), 
                new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> admin() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADMIN, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> candidateCount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CANDIDATECOUNT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> checkState() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CHECKSTATE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> displayCandidate(BigInteger _ID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DISPLAYCANDIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> endElection(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ENDELECTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getVoter(BigInteger ID, String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETVOTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ID), 
                new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> showResults(BigInteger _ID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SHOWRESULTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> showWinner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SHOWWINNER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> startElection(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_STARTELECTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger _ID, String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ID), 
                new org.web3j.abi.datatypes.Address(160, owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> voterCount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_VOTERCOUNT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> voterProfile(String voterAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_VOTERPROFILE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, voterAddress)), 
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

    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(Election.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(Election.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(Election.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(Election.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class AddedACandidateEventResponse extends BaseEventResponse {
        public BigInteger candidateID;

        public String candidateName;
    }

    public static class AddedAVoterEventResponse extends BaseEventResponse {
        public String voter;
    }

    public static class ElectionEndEventResponse extends BaseEventResponse {
        public BigInteger election_state;
    }

    public static class ElectionStartEventResponse extends BaseEventResponse {
        public BigInteger election_state;
    }

    public static class VotedSuccessfullyEventResponse extends BaseEventResponse {
        public BigInteger candidateId;
    }
}*/
