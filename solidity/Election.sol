// SPDX-License-Identifier: UNLICENSED

pragma solidity^0.8.0;

contract Election{
    struct Voter{
        bool isRegistered;
        bool hasVoted;
        uint256 voteTo;
        uint256 weight;
    }

    struct Candidate{
        uint256 ID;
        string name;
    }

    mapping(uint => address) private voterID;
    mapping(address => Voter) private voters;

    mapping(uint256 => Candidate) private candidates;
    mapping(uint256 => uint256) private voteCount;

    address public admin;

    enum state {notstarted, started, ended}

    state electionState;
    uint256 public candidateCount = 0;
    uint256 public voterCount = 0;

    modifier checkIfnotStarted(){
        require(
        (electionState == state.notstarted || electionState == state.ended),
        "The election has already started"
               );
        _;
    }

    modifier checkIfStarted() {
        require(
        electionState == state.started,
        "The election has not started or already ended."
               );
        _;
    }

    modifier checkIfEnded(){
        require(
        electionState == state.ended,
        "The election has not ended yet."
               );
        _;
    }
    
    modifier checkIfNotComplete(){
        require(
        electionState != state.ended,
        "The election has concluded."
               );
        _;
    }

    modifier checkIfVoterValid(address owner) {
        require(
        !voters[owner].hasVoted,
        "The Voter has already voted."
               );
        require(
        voters[owner].weight > 0,
        "The Voter has not voted yet."
               );
        require(
        voters[owner].isRegistered,
        "The voter is not registered."
               );
        _;
    }

    modifier checkIfCandidateValid(uint256 _candidateID){
        require(
        _candidateID > 0 && _candidateID <= candidateCount,
        "Invalid Candidate."
               );
        _;
    }

    modifier checkNotRegistered(address voter) {
        require(
            !voters[voter].hasVoted && voters[voter].weight == 0 && !voters[voter].isRegistered,
            "Voter has already been registered."
        );
        _;
    }
    
    modifier checkAdmin(address owner){
        require(
        owner == admin,
        "Only election admin can access function!"
               );
        _;
    }

    modifier checkNotAdmin(address owner){
        require(
        owner != admin,
        "The election admin cannot access this function"
               );
        _;
    }

    function checkState() view public returns (string memory State)
    {
        if(electionState == state.notstarted)
        return "Election Not Started!!";
        else if(electionState == state.started)
        return "Election Started!!";
        else if(electionState == state.ended)
        return "Election Ended!!";
    }

    function addCandidate(string memory _name, address owner)
        public
        checkAdmin(owner)
        checkIfnotStarted
    {
        candidateCount++;

        candidates[candidateCount].ID = candidateCount;

        candidates[candidateCount].name = _name;

        voteCount[candidateCount] = 0;
    }

    function addVoter(address _voter)
        public
        checkNotAdmin(_voter)
        checkIfnotStarted
        checkNotRegistered(_voter)
    {
        voterCount++;
        voterID[voterCount] = _voter;
        voters[_voter].weight = 1;
        voters[_voter].isRegistered = false;
        voters[_voter].hasVoted = false;
    }
    
    function verifyVoter(address _voter, address owner)
        public
        checkAdmin(owner)
        checkIfnotStarted
        {
           voters[_voter].isRegistered = true; 
        }

    function setAdmin(address owner) 
        public
        checkIfnotStarted
    {
        admin = owner;
        electionState = state.notstarted;
    } 

    function startElection(address owner) public checkAdmin(owner) checkIfnotStarted {
        electionState = state.started;
    }

    function endElection(address owner) public checkAdmin(owner) checkIfStarted{
        electionState = state.ended;
    }

    function vote(uint256 _ID, address owner)
        public
        checkIfStarted
        checkIfVoterValid(owner)
        checkIfCandidateValid(_ID)
    {
        voters[owner].hasVoted = true;
        voters[owner].voteTo = _ID;
        voteCount[_ID] += voters[owner].weight;
        voters[owner].weight = 0;
    }

    function showResults(uint256 _ID)
        public
        view
        checkIfEnded
        checkIfCandidateValid(_ID)
        returns (
            uint256 id,
            uint256 count
        )
    {
        return (_ID, voteCount[_ID]);
    }

    function getCandidateName(uint256 _ID)
        public
        view
        checkIfCandidateValid(_ID)
        returns(
            string memory name
               )
    {
        return candidates[_ID].name;
    }

    function getVoter(uint ID, address owner)  view public checkAdmin(owner)
    returns (
        uint256 id,
        uint256 weight
    )
    {
        return (
            ID,
            voters[voterID[ID]].weight
        );
    }

    function checkElectionStart() view public
        returns(
            bool
               )
        {
            if(electionState != state.started)
                return false;
            return true;
        }
    
    function checkRegistered(address _voter) view public
        returns(
        bool
               )
        {
            return voters[_voter].isRegistered;
    }

    function hasVoted(address _voter) view public
        returns(
        bool
               )
        {
            return (voters[_voter].weight == 0);
        }
}

