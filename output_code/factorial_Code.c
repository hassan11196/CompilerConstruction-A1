int factorial ( int n ) { 
 int temp ; 
 if ( n <= 0 ) { 
 	 temp = 1 ; 
 } 
 else { 
 	 temp = n * factorial(n - 1) ; 
 } 
 	 return temp ; 
 }