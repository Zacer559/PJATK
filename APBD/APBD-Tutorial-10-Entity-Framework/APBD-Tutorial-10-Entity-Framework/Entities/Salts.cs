using System;
using System.Collections.Generic;

namespace APBD_Tutorial_10_Entity_Framework.Entities
{
    public partial class Salts
    {
        public string IdSalt { get; set; }
        public string Value { get; set; }

        public virtual Student IdSaltNavigation { get; set; }
    }
}
