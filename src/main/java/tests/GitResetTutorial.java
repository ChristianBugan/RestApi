package tests;

public class GitResetTutorial {
	
	
	/*
	 * 
	 * 1. Reset all files from staging area
	 * -->  git reset
	 * 
	 * 2. Reset single file from staging area
	 * --> git restore --staged numefisier.java
	 * --> git reset numefisier.java
	 * 
	 * 3. Reset a commit from local repository
	 * --> git reset HEAD^ --> sterge ultimul commit si muta fisierele in working directory
	 * 
	 * 
	 * --> git reset --soft commitID (sterge commit si muta fisierele din commit in staging area)
	 * --> git reset --mixed commitID (sterge commit si Muta fisierele din commit in working directory)
	 * este acelasi lucru cu git reset
	 * --> git reset --hard commitID(sterge commit si sterge fisierele de pe disk)
	 * 
	 * 
	 * --> git reset HEAD~2 --> sterge ultimele commituri (bazat pe numarul dat) si muta 
	 * fisierele respective in working directory
	 * 
	 * 
	 * 3. Reset a merge
	 * 
	 * --> git reset --merge
	 * --> git reset --hard 
	 * 
	 * 4. Reset a commit from remote repo
	 * 
	 * --> git reset HEAD^   (face reset pe local la ultimul commit) 
	 * 
	 * --> git push origin +HEAD  (face push resetului de mai sus) 
	 * 
	 * RESET DOAR PE REMOTE, dar pastra LOCAL commit
	 * --> git push origin +HEAD^:master
	 * 
	 * 
	 * 
	 */

}
