using System;
using System.Collections.Generic;
using System.Text;
using NAI_07_KnapSack.Models;

namespace NAI_07_KnapSack
{
    interface IClassifier
    {
        public static ClassifierResponse Classify(ClassifierRequest request)
        {
            int NumberOfElements = request.Values.Length;
            string BestString = "";
            int BestValue = 0;
            int BestSize = 0;
            for (int i = 0; i < Math.Pow(2, NumberOfElements); i++)
            {
                int current_value = 0;
                int current_size = 0;
                string inBinary = IntToBinary(i, NumberOfElements);
                for (int k = 0; k < inBinary.Length; k++)
                {
                    if (inBinary.ToCharArray()[k] == '1')
                    {
                        current_size += request.Sizes[k];
                        current_value += request.Values[k];
                    }
                }

                if (current_value > BestValue && current_size <= request.Capacity)
                {
                    BestValue = current_value;
                    BestString = inBinary;
                    BestSize = current_size;
                }
            }

            return new ClassifierResponse
            {
                BestValue = BestValue,
                BestSize = BestSize,
                Binary = BestString
            };
        }

        private static String IntToBinary(int number, int length)
        {
            String num = Convert.ToString(number, 2);
            String temp = "";
            for (int j = 0; j < (length - num.Length); j++)
                temp += "0";
            return temp + num;
        }
    }
}