int search ( int n , int[] arr , int v ) { 
 int temp ; 
 if ( n < 0 ) { 
 	 temp = 0 ; 
 } 
 else if ( arr[n]==v && n>=0  ) { 
 	 temp = 1 ; 
 } 
 else if ( arr[n]!=v && n>=0  ) { 
 	 temp = search(n-1,arr,v) ; 
 } 
 	 return temp ; 
 }