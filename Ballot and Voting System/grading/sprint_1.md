#   Grading notes for Sprint 1

-   You haven't tried to run your program, have you?
    ```
    Error: Could not find or load main class edu.unl.cse.csce361.voting_system.Main
    ```
    and, after correcting that problem:
    ```
    Exception in Application start method
    ...
    Caused by: java.lang.NullPointerException: Location is required.
        ...
    	at edu.unl.cse.csce361.voting_system.frontend.Main.start(Main.java:13)
    ```

##  Meeting Minutes

4/4

-   Combining all developer reports into a single section is fine, as long as
    it's useful to you
-   One of your minutes lists four developers for the reports, but it also says
    only three developers were present

##  Scrum Practices

4/5

-   No sprint planning meeting (-0.5)
-   All team members appear to have participated in the minimum of 3 scrum
    meetings (had they not, penalties would have been individual)
-   Scope added to sprint backlog after sprint had begun (-0.5)
-   Sprint backlog was not created until after second scrum meeting -- the
    obvious conclusion is that you'd been working on tasks that weren't part of
    the backlog
-   Few issues created -- greater granularity will make it easier for you to
    monitor your progress

##  Class Diagram

0.6/3

-   Not a class diagram (-1.5)
    -   It's not quite an E-R diagram, either: it appears to have visibility
        modifiers for the fields and does not clearly identify the PKs & FKs
-   Inconsistencies:
    -   BallotEntity.dateOfElection not present in database (-0.1)
    -   CandidateEntity.personID not present in database; CandidateEntity.name
        & CandidateEntity.votes in database but not present in diagram (-0.1)
    -   ElectionOfficialEntity.ElectionOfficialID not present in database (-0.1)
    -   PropositionEntity.yesVotes & PropositionEntity.noVotes in database but
        not present in diagram (-0.1)
    -   VoterBallot table not present in database (-0.25)
-   Not in pdf, jpg, or png format (-0.25)

##  Design

For Sprint 1, I am only looking at whether you have an overt architecture and
are maintaining the separation of concerns.

1.75/2

-   This is a two-layer architecture (?) but there's no integration between
    subsystems (-0.25)

##  Code Style

For Sprint 1, I am making a cursory check for compliance with your team's code
style and running static analysis.

2.7/3

-   Indentation is a mix of tabs & spaces (-0.2)
-   `"from PropositionEntity where id=*"` in
    `PropositionEntity.getPropositions()` will probably fail; HQL isn't quite
    the same as SQL -- if you want all propositions, use
    `"from PropositionEntity"`
-   On your `createQuery()` calls, you're running into the problem of either
    having unparameterized Lists (which will require you to cast the objects
    when extracting them) or unchecked assignments (which could cause a runtime
    type mismatch) -- instead of passing the query string, also pass the class
    type:
    ```
    .createQuery("from PropositionEntity", Proposition.class)
    ```
-   In `CandidateEntity.getCandidatesByRace()`, if
    `"from CandidateEntity where race_id="` doesn't work, try
    `"from CandidateEntity where race="`
-   36 unused imports (-0.1)

##  Commits

-   Watch out for unresolved merge conflicts; these will be penalties for "good
    software engineering practice"

### Messages

2.5/5

-   Non-descriptive/poorly-descriptive messages
    -   Several "Upload <filename>" "Update <filename>" and "Add <filename>"
        shortly before `Sprint_1` tag (-3.0, exceeds maximum penalty)
    -   Several "Upload New File", "Add new file" shortly before `Sprint_1` tag
        (-0.8, already exceeded maximum penalty)
    -   "fixed errors on backend" -- what errors? (-0.1, already exceeded
        maximum penalty)
-   2/54 (4%) commits are malformed  (-0.0)
```
74 commits on the master branch
76 commits among all branches
	On the master branch:
		16 merges, 4 reverts, 52 well-formatted commits, and 2 malformatted commits
		2020-08-02 15:15:54-05:00 Commit a572bb64 has 1 line too long. (maggie.macfadyen)
		2020-08-03 21:33:59-05:00 Commit c27e7a9e has 1 line too long. (Dnisde)
	On all other branches:
		0 merges, 0 reverts, 2 well-formatted commits, and 0 malformatted commits

	Unresolved merge conflicts on the master branch:
		3 out of 16 merges have unresolved merge conflicts:
			2020-08-05 16:37:42-05:00 3ab3e1aa (maggie.macfadyen)
			2020-08-05 21:48:34+00:00 f5eff145 (maggie.macfadyen)
			2020-08-05 21:49:14+00:00 a826dc55 (maggie.macfadyen)
	Unresolved merge conflicts on all other branches:
		0 out of 0 merges have unresolved merge conflicts:
```

### Summary

```
Contributions by each partner to CSCE 361 / summer2020 / 36team3 on 5 branches:
	('Dnisde', 'dede1020qzz@gmail.com')
	  396 total line changes in  12 commits
			md           347 line changes
			xml-TEMPLATE  49 line changes
		First commit:  2020-07-30 07:11:49-05:00
		Median commit: 2020-08-03 21:30:30-05:00
		Last commit:   2020-08-04 20:09:24-05:00
	('Anthony Turner', 'anthony.turner@huskers.unl.edu')
	    1 total line changes in   1 commits
			drawio         1 file changes
		First commit:  2020-08-04 19:03:28-05:00
		Median commit: 2020-08-04 19:03:28-05:00
		Last commit:   2020-08-04 19:03:28-05:00
	('mmacfadyen', 'm.e.macfadyen12@gmail.com')
	   35 total line changes in   2 commits
			java           1 line changes
			xml           34 line changes
		First commit:  2020-08-02 15:44:07-05:00
		Median commit: 2020-08-02 16:27:31-05:00
		Last commit:   2020-08-02 16:27:31-05:00
	('maggie.macfadyen', 'maggie.macfadyen@huskers.unl.edu')
	 1011 total line changes in   4 commits
			gitkeep        0 line changes
			java        1005 line changes
			uml            1 file changes
			umlcd          4 line changes
		First commit:  2020-08-02 15:15:54-05:00
		Median commit: 2020-08-02 19:11:20-05:00
		Last commit:   2020-08-04 12:45:08-05:00
```
