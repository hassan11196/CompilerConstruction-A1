bool palindrome ( char[] s , int len ) { 
 bool temp ; 
 if ( len == 1 ) { 
 	 temp = true ; 
 } 
 else if ( len == 2 ) { 
 	 temp = s[0]==s[1] ; 
 } 
 else if ( len > 2 ) { 
 	 temp = s[len - 1] ^ palindrome(substr(s, 2, len-2), len-2) ; 
 } 
 	 return temp ; 
 }