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

// mpjrun.sh -np 4 DistributedSumDistributedSum

import mpi.*;

public class DistributedSum {
    public static void main(String[] args) throws MPIException {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int localSum = 0;
        int[] recvBuffer = new int[1];

        int elementsPerProcess = array.length / size;
        int extraElements = array.length % size;

        int startIndex = rank * elementsPerProcess + Math.min(rank, extraElements);
        int endIndex = startIndex + elementsPerProcess + (rank < extraElements ? 1 : 0);

        for (int i = startIndex; i < endIndex; i++) {
            localSum += array[i];
        }

        System.out.println("Process " + rank + " intermediate sum: " + localSum);
        MPI.COMM_WORLD.Reduce(new int[] { localSum }, 0, recvBuffer, 0, 1, MPI.INT, MPI.SUM, 0);
        if (rank == 0) {
            System.out.println("Final sum: " + recvBuffer[0]);
        }
        MPI.Finalize();
    }
}