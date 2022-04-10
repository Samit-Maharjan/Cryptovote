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

    enum state {notstarted, started, ongoing, ended}

    state electionState;
    uint256 public candidateCount;
    uint256 public voterCount;

    modifier checkIfnotStarted(){
        require(
        electionState == state.notstarted,
        "The election has already started"
               );
        _;
    }

    modifier checkIfStarted() {
        require(
        electionState == state.started,
        "The election is ongoing or already ended."
               );
        _;
    }

    modifier checkIfOngoing(){
        require(
        electionState == state.ongoing,
        "The election is not ongoing."
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

    event addedAVoter(address voter);

    event votedSuccessfully(uint256 candidateId);

    event electionStart(state election_state);
    event electionEnd(state election_state);
    
    event addedACandidate(uint candidateID, string candidateName);

    constructor(address owner) {
        admin = owner;
        electionState = state.notstarted; 
    }

    function checkState() public view returns (string memory State)
    {
        if(electionState == state.notstarted)
        return "Election Not Started!!";
        else if(electionState == state.started)
        return "Election Started!!";
        else if(electionState == state.ongoing)
        return "Election in Process!!";
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

        emit addedACandidate(candidateCount, _name);
    }

    function addVoter(address _voter, address owner)
        public
        checkAdmin(owner)
        checkNotAdmin(_voter)
        checkIfnotStarted
        checkNotRegistered(_voter)
    {
        voterCount++;
        voterID[voterCount] = _voter;
        voters[_voter].weight = 1;
        voters[_voter].isRegistered = true;
        emit addedAVoter(_voter);
    }

    function startElection(address owner) public checkAdmin(owner) checkIfnotStarted {
        electionState = state.started;
        emit electionStart(electionState);
    }

    function displayCandidate(uint256 _ID)
        public
        view
        returns (
            uint256 id,
            string memory name
        )
    {
        return (
            candidates[_ID].ID,
            candidates[_ID].name
        );
    }

    function showWinner()
        public
        view
        checkIfEnded
        returns (string memory name, uint256 id, uint256 votes)
    {
        uint256 max = 0;
        uint256 maxIndex;
        string memory winner;
        for (uint256 i = 1; i <= candidateCount; i++) {
            if (voteCount[i] > max) {
                winner = candidates[i].name;
                maxIndex = i;
                max = voteCount[i];
            }
        }
        return (winner, maxIndex, max) ;
    }

    function vote(uint256 _ID, address owner)
        public
        checkIfOngoing
        checkIfVoterValid(owner)
        checkIfCandidateValid(_ID)
    {
        voters[owner].hasVoted = true;
        voters[owner].voteTo = _ID;
        voteCount[_ID] += voters[owner].weight;
        voters[owner].weight = 0;
        emit votedSuccessfully(_ID);
    }

    function endElection(address owner) public checkAdmin(owner) {
        electionState = state.ended;
        emit electionEnd(electionState);
    }

    function showResults(uint256 _ID)
        public
        view
        checkIfEnded
        checkIfCandidateValid(_ID)
        returns (
            uint256 id,
            string memory name,
            uint256 count
        )
    {
        return (_ID, candidates[_ID].name, voteCount[_ID]);
    }

    function getVoter(uint ID, address owner)  public view checkAdmin(owner)
    returns (
        uint256 id,
        address voterAddress,
        uint256 weight
    )
    {
        return (
            ID,
            voterID[ID],
            voters[voterID[ID]].weight
        );
    }

    function voterProfile(address voterAddress) public view 
    returns (
        uint256 id,
        uint256 weight,
        uint256 votedTowards,
        string memory name // Candidate voted
        )
    {
         
        for(uint256 i = 1; i<= voterCount; i++)
        {
            if(voterAddress == voterID[i])
            {
                return (
                    i,
                    voters[voterID[i]].weight,
                    voters[voterID[i]].voteTo,
                    candidates[voters[voterID[i]].voteTo].name
                    );
            }
        }
    } 
}

