package com.dnu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class DifferentThreadMethods {

    public int[] generateArrayFixedPoolSimpleThreads(int size) {
        int[] array = new int[size];
        try ( ExecutorService executorService = Executors.newFixedThreadPool(20)) {
            for (int i = 0; i < array.length; i++) {
                final int index = i;
                executorService.submit(() -> {
                    array[index] = index;
                    return array[index];
                });
            }
            executorService.shutdown();
        }
        return array;
    }

    public int[] generateArrayCashedPoolSimpleThreads(int size) {
        int[] array = new int[size];
        try ( ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < array.length; i++) {
                final int index = i;
                executorService.submit(() -> {
                    array[index] = index;
                    return array[index];
                });
            }
            executorService.shutdown();
        }
        return array;
    }

    public int[] generateArrayVirtualThreads(int size) {
        int[] array = new int[size];
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < array.length; i++) {
                final int index = i;
                executorService.submit(() -> {
                    array[index] = index;
                    return array[index];
                });
            }
        }
        return array;
    }


    public int[] generateArrayParallelStreams(int size) {
        return IntStream.range(0, size)
                .parallel()
                .toArray();
    }

    public void mapArraysFixedPoolSimpleThreads(int[] array) {
        try ( ExecutorService executorService = Executors.newFixedThreadPool(30)) {
            for (int i = 0; i < array.length; i++) {
                final int index = i;
                executorService.submit(() -> array[index] = array[index] * 2);
            }
            executorService.shutdown();
        }
    }

    public void mapArraysCashedPoolSimpleThreads(int[] array) {
        try ( ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < array.length; i++) {
                final int index = i;
                executorService.submit(() -> array[index] = array[index] * 2);
            }
            executorService.shutdown();
        }
    }
    public void mapArraysVirtualThreads(int[] array) {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < array.length; i++) {
                final int index = i;
                executorService.submit(() -> array[index] = array[index] * 2);
            }
            executorService.shutdown();
        }
    }

    public void mapArraysParallelStreams(int[] array) {
        IntStream.range(0, array.length)
                .parallel()
                .forEach(i -> array[i] = array[i] * 2);
    }
}