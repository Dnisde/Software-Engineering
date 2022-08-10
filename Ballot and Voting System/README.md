# Voting System

The city of Pacopolis wants you to develop an electronic voting system. While
the mayoral race between Pat Mann and Dawn Keykong is the most visible
election, there are other elections and issues that will also need to be
decided. And, of course, the electronic voting system must be usable in future
elections, too.

-   The ballot for the November 2020 election consists of:
    -   Mayor: Pat Mann vs. Dawn Keykong
    -   City Council seat: Inky vs. Blinky
    -   Sheriff: Q. Burte, no opponent
    -   Proposition 1: Shall there be a 25¢ tax on cherries?
    -   Proposition 2: Shall liquor licenses be required for electronic bars?
    -   Proposition 3: Shall electronic race tracks be held liable for
        electronic car crashes?

-   A non-technical person must be able to create the ballot for a new
    election.

The system shall allow a voter to identify themselves through authentication,
after which they shall be presented with the ballot. After making their
selections, the voter shall be offered the opportunity to review and change
their selections. Once submitted, the voter's choices shall be recorded. The
system shall prohibit a voter from voting more than once in the same election.
At the end of the voting day, the system shall determine the winner of each
election and the outcome of each issue.

While it shall be possible for a voter to later view their recorded vote to
confirm that it was recorded correctly, and it shall be possible for someone
such as an auditor to determine *whether* a particular voter voted, it shall
be impossible for anyone other than the voter to determine *how* any
particular voter voted.

-   "Anyone other than the voter" includes database administrators and
    system administrators.

The system shall allow an unlimited number of voters to vote from their own
computers or from a shared computer at a polling location.

-   The system may be implemented in text-mode or with a GUI.

# Guideline of Pacopolis Online Voting System:

## Register for an account

-   Register as an Election Official(Auditor Include) in the voting system:

    -   Ability: Election Official is able to create a brand new ballot by using the voting system. Also, Election Official could check the current races and proposition's status; For instance,
     		-   how many voters has voted within the system, and how did they voted.
        -   Search for a certain user's voting status, check if they voted or not.

    -   Suggestion and Warning:
        1. Once an Election Official trying to create a new ballot by press the "Submit" Button, the old ballot from the latest one will be dropped and deleted. Because the system will treat the  old ballot competition has finished as default.
        2.  The zip-code should be exactly 5 digits that corresponds with the area. **Be careful when you register a new account for the system.**

-   Register as an Voter in the voting System:

    -   Ability: The voter could vote for current candidates of different races, and also for propositions of the ballot which is available.

    -   Restriction:
        1.  A voter could be only able to choose a certain candidate for a single race, and a certain proposition answer for a ballot, and can only vote one time.
        2.  Once submitted, the user could not being able to modify their decision, however, they could review their choices when they trying to login in for the second time.
        3.  **A voter should be able to reach the legal age of 18 years old to being able to vote for a ballot. Be careful with register when you choosing the age.**
        4.  The zip-code should be exactly 5 digits that corresponds with the area. **Be careful when you register a new account for the system.**

## Login into System:

-   FirstName:   FirstName of the Person (Election official or Voter)
-   LastName:    LastName of the Person (Election official or Voter)
-   Social Security Number:    Social Security Number of the Person (Election official or Voter)

## Login as a formal voter account:

-   First time of login:

    1. You will being able to choose and select different kinds of candidates and proposition when you first trying to login with Pacopolis Online Voting System. Each candidates corresponds with a single race for the exist ballot, and each proposition corresponds two choices for a ballot.
    2. Once you submit your answer, you could view your choices but not able to make more changes.

-   After first time of login(After Voted):
    -   You will only being able to review your recent choices for the ballot of each candidates with races, each propositions with ballot.

-   Button: View Election Results:
    -   As a person, whether you are register as a Voter or Election Official, you would be able to review the current Election Results for the ballot based on the votes. Details are who is going to win the candidates of the corresponding races, and which proposition would like to pass.

## Login as a formal Election Official:

-   You could login as many times you want, because you are the administrators for this system, or you could say you are Auditor.

    -   Create New ballot:

        -   Enter Race Position:
            -   The Race position that you would like to submit for the ballot. **(Only Alphabetical Numbers allows, without any illegal symbol or numbers)**
        -   Enter Candidates
            -   The Candidates of the race you would like to submit for the race of the ballot. **(Only Alphabetical Numbers allows, without any illegal symbol or numbers, no spaces between each candidates name)**
        -   Enter Proposition Title:
            -   The title of the proposition that you would like to submit for the ballot.
        -   Enter Proposition Description:
            -   The proposition Description that you would like to submit that corresponds with Title of the ballot.

        -   **You could add as many as Candidates for each Races, and Proposition if that necessary.**

    -   Check Voter History:

        -   Check with the current status of a single voter. Entering the FirstName, LastName and Social Security Number would be able to find if a voter votes for the current ballot or not.

    -   Check Voter's Ballot:

        -   Check with the summery of the current races and proposition's status of the current ballot based on the Voter's votes. How many voters voted for the ballot, and how they voted.
