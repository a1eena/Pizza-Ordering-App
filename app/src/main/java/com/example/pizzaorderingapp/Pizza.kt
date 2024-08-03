package com.example.pizzaorderingapp

enum class PizzaType {
    VEG, NON_VEG
}

data class Pizza(
    val title: String,
    val image: Int,
    val price: Double,
    val pizzaType: PizzaType,
)

data class Topping(
    val title: String,
    val price: Double,
    var isChecked: Boolean = false,
)

fun dummyPizzaRepository(): List<Pizza>{
    return listOf(
        Pizza("Cheese Pizza", R.drawable.cheese, 199.00,  PizzaType.VEG),
        Pizza("Margherita Pizza", R.drawable.margherita, 199.00,  PizzaType.VEG),
        Pizza("Tomato Special Pizza", R.drawable.tomato, 199.00,  PizzaType.VEG),
        Pizza("Veg Exclusive Pizza", R.drawable.veg, 299.00,  PizzaType.VEG),
        Pizza("Hawaii Pineapple Pizza", R.drawable.pineapple, 399.00,  PizzaType.VEG),
        Pizza("Non-Veg Pizza", R.drawable.nonveg, 399.00,  PizzaType.NON_VEG),
        Pizza("Loaded Pizza", R.drawable.loaded, 499.00,  PizzaType.NON_VEG),
        Pizza("Chicken Exclusive Pizza", R.drawable.chicken, 499.00,  PizzaType.NON_VEG),
        Pizza("Shrimp Pizza", R.drawable.shrimp, 399.00,  PizzaType.NON_VEG),



    )

}

fun topicRepository(): List<Topping> {
    return listOf(
        Topping("Cheese", 125.00),
        Topping("Tomato", 25.00),
        Topping("Onion", 20.00),
        Topping("Capsicum", 60.00),
        Topping("Mushroom", 50.00),
        Topping("Black Olive", 100.00),
        Topping("Pineapple", 50.00),
        Topping("Jalapeno", 75.00),
        Topping("Sweetcorn", 20.00),
        Topping("Broccoli", 50.00),



    )
}
