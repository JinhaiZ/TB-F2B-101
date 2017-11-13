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