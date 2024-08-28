

//chunk的含义就是：逐条的(Read)，等凑齐chunk数量后再对这一批进行逐条(Process)，然后等process凑齐chunk数量后，再对这一批进行(Write)

JobInstance = Job + Job Parameter
![springbatch-architecture.png](springbatch-architecture.png)

![springbatch-chunk-process-flow.png](springbatch-chunk-process-flow.png)

