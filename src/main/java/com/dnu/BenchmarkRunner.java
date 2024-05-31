package com.dnu;



import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


@State(Scope.Benchmark)
public class BenchmarkRunner {

    DifferentThreadMethods threads = new DifferentThreadMethods();
    public final int size = 1000000;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void testMethodFixedPoolThreads() {
        int[] array = threads.generateArrayFixedPoolSimpleThreads(size);
        threads.mapArraysFixedPoolSimpleThreads(array);
    }

    /*@Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void testMethodCashedPoolThreads() {
        int[] array = threads.generateArrayCashedPoolSimpleThreads(size);
        threads.mapArraysCashedPoolSimpleThreads(array);
    }*/


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void testMethodVirtualThreads() {
        int[] array = threads.generateArrayVirtualThreads(size);
        threads.mapArraysVirtualThreads(array);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void testMethodParallelStreams() {
        int[] array = threads.generateArrayParallelStreams(size);
        threads.mapArraysParallelStreams(array);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkRunner.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .timeUnit(TimeUnit.MILLISECONDS)
                .build();

        new Runner(opt).run();
    }
}