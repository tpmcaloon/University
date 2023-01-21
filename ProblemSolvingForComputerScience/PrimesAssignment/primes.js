//TASK ONE
//this should return the index of the first element with a value larger than item
function minBinarySearch(array, item) {
	var l = 0;
	var r = array.length - 1;
	if (r == 0) 
	{
		return false;
	}
	// complete the following code, which has been commented out (and uncomment it)
	// put in something for MISSING1, MISSING2 and MISSING3
	while (r >= l) 
	{
		var m = Math.floor((l + r) / 2);
        if (array[m] === item) 
		{
			return m+1;
        } else if (array[m] < item) 
		{
            l = m + 1;
        } else 
		{
            r = m - 1;
        }
    }
	return l;
}

//TASK TWO
//this function should return an array that stores all prime numbers from 2 up to (and possibly including) n
function genPrimes(n) {
	// this generates an array storing two-element arrays: each element stores an integer i and the Boolean true
	var array = [];
	for (var i = 2; i <= n; i++) {
		array.push([i,true]);
	}
	// complete the code here to implement the Sieve of Eratosthenes
	// you need to implement steps 2 to 5 in the algorithm below
	// if the value stored in array is not prime then the associated Boolean should be changed to false
	// you should implement two nested loops

	for (var i = 2; i <= Math.ceil(Math.sqrt(n)); i++) 
    {
        if (array[i-2][1] == true)
        { 
			for (var j = 2; j <= n / i; j++)
			{
				array[(i*j) - 2][1] = false;
			}
		}
	}

	// below here implements step 6 and does not need to be altered
	// this will loop over all elements in array and if the Boolean in each element is true, then it pushes that number to out
	// out will store all primes up to (and possibly including) n
	var out = [];
	for (var i = 0; i < array.length; i++)
	{
		if (array[i][1]) 
		{
			out.push(array[i][0]);
		}
	}
	return out;
}

//TASK THREE
//this function should randomly generate a prime of length len where len should be an integer greater than 0
function randomPrime(len) {
	//complete the code to return a random prime number of length len, i.e. with len digits

	//this makes an array storing all the prime numbers with at most len digits
	var primes = genPrimes(10**(len)-1);
	var search = minBinarySearch(primes, 10 **(len - 1))
	var output = Math.floor(Math.random() * (primes.length - search) ) + search

	return primes[output]
	//remember that the smallest number with len digits is 10**(len-1), and the largest number of length len is 10**(len)-1
}

console.log(randomPrime(2)) ;

//TASK FOUR
//this function should return the two primes in an array if n is a semiprime, return the input n if it is prime, and false otherwise
function semiPrimes(n){
	//complete this function
	var semiPrime = genPrimes(n)
	var prime = semiPrime.length;
	
	for(var i = 0; i < prime; i++)
	{
		if(semiPrime[i] == n)
		{
			return n;
		}
		for(var j = 0; j < prime; j++)
		{
			if(semiPrime[i]*semiPrime[j] == n) 
			{
				return [semiPrime[i], semiPrime[j]]
			}
		}
	}
	return false;
}
	console.log(semiPrimes(6)); 
	console.log(semiPrimes(5)); 
	console.log(semiPrimes(8));



// DO NOT DELETE BELOW THIS LINE OR NOTHING WILL WORK

module.exports = {minBinarySearch : minBinarySearch, genPrimes : genPrimes, randomPrime : randomPrime, semiPrimes : semiPrimes}
