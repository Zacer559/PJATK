using System;
using System.Collections.Generic;
using System.Text;

namespace NAI_07_KnapSack.Models
{
    public class ClassifierRequest
    {
        public int[] Values { get; set; }
        public int[] Sizes { get; set; }
        public int Capacity { get; set; }
    }
}