## Exercise 1

This is an initial exercise in order to discover the MPI environment and its tools.

- generate the executable file with the Unix `make` command start the MPI program with the mpirun command and indicate the name of the executable mpi-example and give in the argument a string of characters
    1. clean generated files `make clean`
    2. generate executable files with default config (generate logfile mpi-example.clog2) `make`
    3. generate executable files with traces print to standard output when runs the executable file `make trace`
    4. generate executable files with logfile after running the executable `make log` same with `make`
    5. generate executable files without traces nor logfile `make std`

    the following outputs are acqured from running executable generate with `make trace` command

    ```bash
    jzhou@pc-info-199:~/TP_MPI_2016/Exo1$ mpirun --np 1 -- mpi-example hello
    Starting MPI_Init...
    [0] Ending MPI_Init
    [0] Starting MPI_Comm_rank...
    [0] Ending MPI_Comm_rank
    [0] Starting MPI_Comm_size...
    [0] Ending MPI_Comm_size
    Number of arguments in the command line 2  
    I'll convert  hello using 0 slaves 
    [0] Starting MPI_Finalize...
    [0] Ending MPI_Finalize
    ```

    ```bash
    Starting MPI_Init...
    Starting MPI_Init...
    [0] Ending MPI_Init
    [0] Starting MPI_Comm_rank...
    [0] Ending MPI_Comm_rank
    [0] Starting MPI_Comm_size...
    [0] Ending MPI_Comm_size
    Number of arguments in the command line 2  
    I'll convert  hello using 1 slaves 
    Process sending hello to 1
    [0] Starting MPI_Send with count = 5, dest = 1, tag = 201...
    [1] Ending MPI_Init
    [1] Starting MPI_Comm_rank...
    [1] Ending MPI_Comm_rank
    [1] Starting MPI_Comm_size...
    [1] Ending MPI_Comm_size
    [1] Starting MPI_Recv with count = 100, source = 0, tag = 201...
    [0] Ending MPI_Send
    [0] Starting MPI_Recv with count = 100, source = 1, tag = 202...
    [1] Ending MPI_Recv from 0 with tag 201
    [1] Starting MPI_Send with count = 7, dest = 0, tag = 202...
    [1] Ending MPI_Send
    [1] Starting MPI_Finalize...
    [0] Ending MPI_Recv from 1 with tag 202
    Process receiving HELLO from node 1 
    [0] Starting MPI_Finalize...
    [1] Ending MPI_Finalize
    [0] Ending MPI_Finalize
    ```

    ```bash
    jzhou@pc-info-199:~/TP_MPI_2016/Exo1$ mpirun --np 3 -- mpi-example hello
    Starting MPI_Init...
    Starting MPI_Init...
    Starting MPI_Init...
    [0] Ending MPI_Init
    [0] Starting MPI_Comm_rank...
    [0] Ending MPI_Comm_rank
    [0] Starting MPI_Comm_size...
    [0] Ending MPI_Comm_size
    Number of arguments in the command line 2  
    I'll convert  hello using 2 slaves 
    Process sending he to 1
    [0] Starting MPI_Send with count = 2, dest = 1, tag = 201...
    [0] Ending MPI_Send
    Process sending llo to 2
    [0] Starting MPI_Send with count = 3, dest = 2, tag = 201...
    [0] Ending MPI_Send
    [0] Starting MPI_Recv with count = 100, source = 1, tag = 202...
    [0] Ending MPI_Recv from 1 with tag 202
    Process receiving HE from node 1 
    [0] Starting MPI_Recv with count = 100, source = 2, tag = 202...
    [0] Ending MPI_Recv from 2 with tag 202
    Process receiving LLO�� from node 2 
    [0] Starting MPI_Finalize...
    [2] Ending MPI_Init
    [2] Starting MPI_Comm_rank...
    [2] Ending MPI_Comm_rank
    [2] Starting MPI_Comm_size...
    [2] Ending MPI_Comm_size
    [2] Starting MPI_Recv with count = 100, source = 0, tag = 201...
    [2] Ending MPI_Recv from 0 with tag 201
    [2] Starting MPI_Send with count = 7, dest = 0, tag = 202...
    [2] Ending MPI_Send
    [2] Starting MPI_Finalize...
    [1] Ending MPI_Init
    [1] Starting MPI_Comm_rank...
    [1] Ending MPI_Comm_rank
    [1] Starting MPI_Comm_size...
    [1] Ending MPI_Comm_size
    [1] Starting MPI_Recv with count = 100, source = 0, tag = 201...
    [1] Ending MPI_Recv from 0 with tag 201
    [1] Starting MPI_Send with count = 3, dest = 0, tag = 202...
    [1] Ending MPI_Send
    [1] Starting MPI_Finalize...
    [0] Ending MPI_Finalize
    [2] Ending MPI_Finalize
    [1] Ending MPI_Finalize
    ```

- Activate the various traces and understand their meanings and how they can contribute to the debugging phase of an application.

    traces can help us debug, we know which machines are running which functions, as is shown above


## Exercise 2

- You can generate an executable file from the Makefile with the Unix command make : this program “mpi-ring” requires an integer argument. By setting different values and running it, understand how the program works and what it does.

- This programs does not stop correctly (it does not terminate well and there are pending
messages). Modify the code so that the termination is correct.

## Exercise 3

- Execute make and start the execution on the cluster (a set of PCs) and provide a directory path as the argument (e.g.; /etc).

- This program is very similar to the Unix du (disk usage) command. This Unix command computes the size of a file tree. Here we split the load of the computation by splitting the tree and assigning to each of the processor one sub tree (in an iterative way).

- From the traces, what can you determine on the load balancing features? Explain how the master and the slaves work. Explain why and how the slowest slaves constraints the others.

- Propose an alternate solution where the slave (a nice and willing slave !) asks for work to perform, code it and analyze the outcomes and benefits.






## Exercise 4


The aim of this exercise is to computer Mandelbrot set and produce interesting visual results. Mandelbrot set requires intensive computations and therefore are good candidate to be run on a cluster. You can find an interesting survey on the topic at : http://www.math.utah.edu/~alfeld/math/mandelbrot/mandelbrot.html for this exercise, we should monitor the complexity and the computation time of the Mandelbrot set. Use the various MPI functions as defined in Exo0 directory.

- in the source, you can generate mandel-basic-mpi which provides a slow and complex solution. You can execute it and display the result with the Unix command  

    `xv mandel.ppm`
- explain why (from an analyse of the trace) why it takes so long to compute the image. Indicate in particular what is the complexity of the solution implemented (in terms of messages that are exchanged).
    - complexity: O(NX\*NY) since we need NX*NY times of message exchange. NX, NY 
    are image hight and width respectively 
    - also, no matter how many nodes are involved in the calculation,
    they all calculate the same image. No job disribution is made from master node.
    thus, it takes so long to compute the image and the time taken is invariant to
    number of slaves, as explained before

- Propose a modification of the program: instead that a point is computed, the slave computes a complete line (instead sending one single point, we send a complete line of points)
    - this can reduce the time complexity to O(NX) or O(NY) if slaves computes a
    complete line with lenght of image width of image hight
    - but the programme still not use parallel computing (distribute job betwenn 
    slaves)

- Check that the program still works and produces a similar image after your alteration.

- In a second stage, you can send a block of lines instead of a single line. Measure the impact on the response time.
    - it can continue reduce the time complexity to O(NX/H) with H the hight of one 
    block
    - but the programme still not use parallel computing (distribute job betwenn 
    slaves)

- Modify then the various parameters of the Mandelbrot set to generate different images!

如果要让程序运行的更快，master node得向不同的slave指派不同的任务，比如有１６个block的图像要进行计算，同时我们有4个slave，我们可以让每个slave计算一个block，当一个slave计算完成时再指派一个新的block给它计算，直到16个block都计算完成。这样才算利用到了MPI的并行计算框架


## 一个典型的MPI分布式程序

```c
#include <stdio.h>
#include <string.h>
#include "mpi.h"


int main(int argc, char *argv[])
{
  MPI_Status status;
  int i,j, num, rank, size, tag,   nbslaves;
  char inputstr[100], outstr[100];

  /* Start up MPI */

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);
 
  /* Arbitrarily choose 201 to be our tag.  */

  tag = 201;

  nbslaves = size -1;

  if (rank == 0) { /* Begin User Program  - the master */
    /* send data to slave tasks */
    for( i=1 ; i < nbslaves+1 ; i++ ) {
      /* prepare the data to be sent 
     There should be a better way with C string functions ....  */
     /* JOB DISTRIBUTION HERE*/
      MPI_Send(outstr, strlen(outstr), MPI_CHAR, i, tag, MPI_COMM_WORLD); 
    }
    /* get results from slaves*/
    for( i=1 ; i < nbslaves+1 ; i++ ) {
      MPI_Recv(&inputstr, 100, MPI_CHAR, i, tag+1, MPI_COMM_WORLD, &status);
    }
  }
  else { /* Slave */
    /* receive job from master */
    MPI_Recv(&inputstr, 100, MPI_CHAR, 0, tag, MPI_COMM_WORLD, &status);
    /* DO JOB HERE */
    /* sent result back to master */
    MPI_Send(outstr, strlen(outstr)+1 , MPI_CHAR, 0, tag+1, MPI_COMM_WORLD); 
  }

  MPI_Finalize();
  return 0;
}
```