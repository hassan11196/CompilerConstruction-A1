int fibonacci ( int n ) { 
 int temp ; 
 if ( n == 0 ) { 
 	 temp = 0 ; 
 } 
 else if ( n == 1 ) { 
 	 temp = 1 ; 
 } 
 else if ( n >= 2 ) { 
 	 temp = fibonacci(n-2) + fibonacci(n-1) ; 
 } 
 	 return temp ; 
 }