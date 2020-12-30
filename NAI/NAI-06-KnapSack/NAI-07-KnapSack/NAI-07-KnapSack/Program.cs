using System;
using NAI_07_KnapSack.Models;

namespace NAI_07_KnapSack
{
    class Program : IClassifier
    {
        static void Main(string[] args)
        {
            //Init data
            int[] sizes = {4, 3, 10, 7, 7, 9, 6, 3, 4, 6, 1, 6, 7, 9, 1, 3, 4, 10, 5, 4, 2, 7, 4, 6, 9, 4};
            int[] values = {9, 4, 1, 9, 17, 19, 19, 6, 8, 4, 16, 3, 4, 14, 7, 12, 20, 4, 8, 7, 15, 9, 5, 2, 11, 4};
            int capacity = 70;

            var Request = new ClassifierRequest
            {
                Capacity = capacity,
                Sizes = sizes,
                Values = values
            };
            var result = IClassifier.Classify(Request);
            Console.WriteLine(" For binary: " + result.Binary + " the value was " + result.BestValue + " and size " + result.BestSize);
        }
    }
}