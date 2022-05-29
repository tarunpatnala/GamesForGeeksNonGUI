public enum Rating {
    G{
        public String toString() {
            return "General Audience";
        }
    },
    M{
        public String toString() {
            return "Matured Audience";
        }
    },
    MA{
        public String toString() {
            return "Matured Audience Aged 15+ only";
        }
    },
    NA{
        public String toString() {
            return "Not Rated";
        }
    },
    PG{
        public String toString() {
            return "Parental Guidance";
        }
    },
    R{
        public String toString() {
            return "Restricted Audience Aged 18+ only";
        }
    },
    None{
        public String toString() {
            return "None";
        }
    }
}
