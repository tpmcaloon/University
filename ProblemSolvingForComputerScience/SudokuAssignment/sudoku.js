//TASK ONE

function makeRows(row) {
	var puzzle = [];
     for(var i = 0; i < 4; i++)
         {
             puzzle.push(row.slice());
         }
    
    return puzzle;
    
}
//var row = [1 , 2 , 3 , 4];
//console.log ( makeRows (row) ) ;

//TASK TWO
// this is the constructor of the queue data structure
function Queue() {
	this.arr = [];
	this.head = function() {
		return this.arr[0];
	};
	this.dequeue = function() {
		if (this.arr.length == 0) {
			return "Queue underflow!";
		} else {
			return this.arr.shift();
		}
	};
	this.enqueue = function(o) {
		this.arr.push(o);
	};
	this.isEmpty = function() {
			return this.arr.length == 0;
	};
}

function permuteRow(row, p)  {
	var queue = new Queue();
	var n = row.length;
	for (var i = 0; i < row.length; i++) 
	{
    // complete this loop to enqueue the values in the array to the queue
	queue.enqueue(row[i]);
}
while(p > 0)
{
	queue.enqueue(queue.head());
	queue.dequeue();
	p--;
}
return queue.arr;

//    var row = [1 , 2 , 3 , 4];
//console . log ( permuteRow (row , 2) ) ;

}

//    var row = [1 , 2 , 3 , 4];
//console . log ( permuteRow (row , 2) ) ;


function permutePuzzle(puzzle, p, q, r) {
	var perms = [p,q,r];
    for (var i=0; i<3; i++)
	{
        puzzle[i + 1] = permuteRow(puzzle[i + 1], perms[i]);
    }
    return puzzle;
}

//TASK THREE

function linearSearch(array, item) {
	var n = array.length;
	for (var i = 0; i < n; i++)
	{
		if (array[i] == item) 
		{
			return true;
		}
	}
	return false;
}

function checkColumn(puzzle, j) {
//first make an array out of the values stored in column j
    var check = [];
    for(var i = 0; i < 4; i++)
	{
        check.push(puzzle[i][j]);
    }

    //call linearSearch on the array of column values for all values of k from 1 to 4
	for( var i = 1; i < 5; i++)
	{
		if(linearSearch(check,i) == false)
		{
			return false;
		}
	}
	return true;

}

//var puzzle = [[1 , 2 , 3 , 4] , [2 , 3 , 4 , 1] , [3 , 4 , 1 , 2] , [4 , 1 , 2 , 3]];
//console . log ( checkColumn ( puzzle , 1) ) ;
//puzzle = [[1 , 2 , 3 , 4] , [2 , 3 , 4 , 1] , [2 , 3 , 4 , 1] , [4 , 1 , 2 , 3]];
//console . log ( checkColumn ( puzzle , 2) ) ;




//TASK FOUR

function colCheck(puzzle) {
for(var i = 0; i < 4; i++)
{
    if(checkColumn(puzzle,i)==false)
	{
        return false;
    }
}
    return true;
}
//var puzzle = [[1 , 2 , 3 , 4] , [2 , 3 , 4 , 1] , [3 , 4 , 1 , 2] , [4 , 1 , 2 , 3]];
//console . log ( colCheck ( puzzle ) ) ;
//puzzle = [[1 , 2 , 3 , 4] , [2 , 3 , 4 , 1] , [2 , 3 , 4 , 1] , [4 , 1 , 2 , 3]];
//console . log ( colCheck ( puzzle ) ) ;

//TASK FIVE

function makeGrid(puzzle, row1, row2, col1, col2) {
	//this copies all elements in a grid from co-ordinates (row1, col1) to (row2,col2) to an array
	var array = [];
	for (var i = row1; i <= row2; i++) 
	{
		for (var j = col1; j <= col2; j++) 
		{
			array.push(puzzle[i][j]);
		}
	}
	return array;
}

function checkGrid(puzzle, row1, row2, col1, col2) {
    var grid = makeGrid(puzzle, row1, row2, col1, col2);
    for(i = 0; i < 4; i++)
	{
        if(!linearSearch(grid, i + 1))
		{
            return false;
        }
    }
    return true

}
//var puzzle = [[1 , 2 , 3 , 4] , [2 , 3 , 4 , 1] , [3 , 4 , 1 , 2] , [4 , 1 , 2 , 3]];
//console . log ( checkGrid (puzzle , 0 , 1 , 2, 3) ) ;
//puzzle = [[1 , 2 , 3 , 4] , [3 , 4 , 1 , 2] , [4 , 1 , 2 , 3] , [4 , 1 , 2 , 3]];
//console . log ( checkGrid (puzzle , 0 , 1 , 0, 1) ) ;


//TASK SIX

function checkGrids(puzzle) {
	for(var i = 0; i < 2; i++)
	{
		for(var j = 0; j < 2; j++)
		{
			if(!checkGrid(puzzle,(2 * i),(2 * i + 1),(2 * j),(2 * j + 1)))
			{
				return false;
			}
		}
	}
	return true;
}
var puzzle = [[1 , 2 , 3 , 4] , [2 , 3 , 4 , 1] , [3 , 4 , 1 , 2] , [4 , 1 , 2 , 3]];
//console . log ( checkGrids ( puzzle ) ) ;
puzzle = [[1 , 2 , 3 , 4] , [3 , 4 , 1 , 2] , [4 , 1 , 2 , 3] , [2 , 3 , 4 , 1] ,];
//console . log ( checkGrids ( puzzle ) ) ;

//TASK SEVEN

function makeSolution(row) {
	var solution = [];
	var puzzle = makeRows(row);
	for ( var i = 0; i < 4; i++)
	{
		for ( var j = 0; j < 4; j++)
		{
			for (var k = 0; k < 4; k++)
			{
				solution = permutePuzzle(puzzle, i, j, k);
				if(checkGrids(solution))
				{
					if(colCheck(solution))
					{
						return solution
					}
				}
			}
		}
	}
}
var row = [1 , 2 , 3 , 4];
//console . log ( makeSolution ( row) ) ;

// TASK EIGHT

// a function to randomly select n (row,column) entries of a 2d array
function entriesToDel(n) {

	var array = [];
	var runCheck;
	var duplication;

	for(var i = 0; i < n; i++)
		{
			var row = Math.round(3*Math.random());
			var col = Math.round(3*Math.random());
			runCheck = [row,col];
		
		for (var j = 0; j < array.length; j++)
		{
			if(array[j][0] == runCheck[0] && array[j][1] == runCheck[1])
			{
				duplication = true;
				break;
			} 
			else
			{
				duplication = false;
			}
		}

		if (duplication)
		{
			i--
		}
		else
		{
			array.push(runCheck)
		}
	}
	return array
}


function genPuzzle(row, n) {
	if (n >= 16) {
		return "Error! Too many blank spaces!";
	}
	var solution = makeSolution(row);
	var blanks = entriesToDel(n);
	for (var i = 0; i < blanks.length; i++) {
		solution[blanks[i][0]][blanks[i][1]] = " ";
		console.log(blanks)
	}
	return solution;
}

// The following function is used to visualise the puzzles

function visPuzzle(puzzle) {
	var viz = "";

	for (var i = 0; i < puzzle.length; i++) {
		for (var j = 0; j < puzzle.length; j++) {
			viz = viz + "----";
		}
		viz = viz + "-\n";
		for (var j = 0; j < puzzle.length; j++) {
			viz = viz + "| " + puzzle[i][j] + " ";
		}
		viz = viz + "| " + "\n";
	}
	for (var j = 0; j < puzzle.length; j++) {
			viz = viz + "----";
	}
	viz = viz + "-";

	return viz;
}

console.log(visPuzzle(genPuzzle(row, 12)))

// DO NOT DELETE BELOW THIS LINE OR NOTHING WILL WORK AND YOU MAY GET NO MARKS

module.exports = {makeRows : makeRows, makeSolution : makeSolution, genPuzzle : genPuzzle, checkGrid : checkGrid, checkGrids : checkGrids, colCheck : colCheck, checkColumn : checkColumn, permuteRow : permuteRow, permutePuzzle : permutePuzzle};