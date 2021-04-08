package inventory.utils;

public final class ExceptionNames {
    public static final class PartExceptions {
        public static final String emptyName = "A name has not been entered. ";
        public static final String negativePrice = "The price must be greater than 0. ";
        public static final String negativeStock = "Inventory level must be greater than 0. ";
        public static final String minHigherThanMax = "The Min value must be less than the Max value. ";
        public static final String negativeMinValue = "The Min value can't be negative. ";
        public static final String negativeMaxValue = "The Max value can't be negative. ";
        public static final String stockLowerThanMin = "Inventory level is lower than minimum value. ";
        public static final String stockHigherThanMax = "Inventory level is higher than the maximum value. ";

        // In house part
        public static final String negativeMachineId = "Machine Id can't be negative!";

        // Outsourced part
        public static final String nullCompanyName = "Company name can't be null!";
    }
}