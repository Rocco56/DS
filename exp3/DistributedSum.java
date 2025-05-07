// rm -rf ~/mpj

// wget https://sourceforge.net/projects/mpjexpress/files/latest/download -O mpj.zip

// unzip mpj.zip -d ~/mpj

// ls ~/mpj/mpj-v0_44/lib

// export MPJ_HOME=~/mpj/mpj-v0_44
// export PATH=$PATH:$MPJ_HOME/bin

// echo 'export MPJ_HOME=~/mpj/mpj-v0_44' >> ~/.bashrc
// echo 'export PATH=$PATH:$MPJ_HOME/bin' >> ~/.bashrc
// source ~/.bashrc

// javac -cp $MPJ_HOME/lib/mpj.jar DistributedSum.java

// mpjrun.sh -np 4 DistributedSum


import mpi.*;

class DistributedSum{
    public static void main(String [] args) throws MPIException{
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int localSum = 0;
        int [] array = {1,2,3,4,5,6,7,8,9,10};
        int [] recvBuffer = new int[1];
        int startIndex = (rank)*(array.length/size);
        int endIndex = (rank+1)*(array.length/size);
        for(int i = startIndex;i<endIndex;i++){
            localSum+=array[i];
        }
        System.out.println("Process: "+rank+" intermediate Sum: "+localSum);
        MPI.COMM_WORLD.Reduce(new int[]{localSum},0,recvBuffer,0,1,MPI.INT,MPI.SUM,0);
        if(rank == 0){
            System.out.println("Final Sum: "+recvBuffer[0]);
        }
        MPI.Finalize();


    }
}