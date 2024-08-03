package com.example.pizzaorderingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pizzaorderingapp.ui.screens.CheckoutScreen
import com.example.pizzaorderingapp.ui.screens.PizzaSelectionScreen
import com.example.pizzaorderingapp.ui.screens.ToppingSelectionScreen
import com.example.pizzaorderingapp.ui.theme.PizzaOrderingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PizzaOrderingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                      var navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "pizza_selection") {
                        composable("pizza_selection") {
                            PizzaSelectionScreen(
                                modifier = Modifier.padding(innerPadding)
                            ){
                                navController.navigate("topping_selection/$it")
                            }
                        }
                        composable("topping_selection/{pizzaId}",
                           arguments = listOf(navArgument("pizzaId") {
                              type = NavType.IntType
                           })
                        ){
                            ToppingSelectionScreen(
                                modifier = Modifier.padding(innerPadding),
                                pizzaId = it.arguments?.getInt("pizzaId") ?: 0
                            ){ pizzaId, toppings ->
                                val pizzaPrice = dummyPizzaRepository()[pizzaId].price
                                var totalToppingPrice = 0.00
                                toppings.forEach{
                                    totalToppingPrice +=it.price
                                }
                                val totalAmt = pizzaPrice + totalToppingPrice
                                navController.navigate("checkout/$totalAmt")

                            }
                        }
                        composable("checkout/{amt}",
                            arguments = listOf(navArgument("amt"){
                                type = NavType.FloatType
                            })
                            ){
                            val amt = it.arguments?.getFloat("amt") ?: 0f
                            CheckoutScreen(
                                modifier = Modifier.padding(innerPadding),
                                totalamt = amt,
                            )
                        }


                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PizzaSelectionPreview() {
    PizzaSelectionScreen(){

    }
}

@Preview(showBackground = true)
@Composable
private fun ToppingSelectionPreview() {
    ToppingSelectionScreen(pizzaId = 0){_, _ ->}

}

@Preview
@Composable
private fun CheckoutPreview() {
    CheckoutScreen(totalamt = 800f)


}