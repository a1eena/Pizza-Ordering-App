package com.example.pizzaorderingapp.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzaorderingapp.Topping
import com.example.pizzaorderingapp.dummyPizzaRepository
import com.example.pizzaorderingapp.topicRepository

@Composable
fun ToppingSelectionScreen(
    modifier: Modifier = Modifier,
    pizzaId: Int,
    onConfirm: (Int, Set<Topping>) -> Unit,
) {
    val pizza = dummyPizzaRepository()[pizzaId]
    var selectedToppings by remember { mutableStateOf(emptySet<Topping>()) }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.scrim)

    ) {
        Column {
            Text(
                text = "Select Toppings",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                modifier = Modifier.padding(8.dp),

                )
            Text(
                text = "${pizza.title} Selected",
                color = MaterialTheme.colorScheme.error,
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp)

            )
            Spacer(modifier = Modifier.padding(8.dp))
            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
            ) {
                items(topicRepository()) {
                    ToppingItem(topping = it) { selection ->
                        if (selection.isChecked) {
                            selectedToppings = selectedToppings.plusElement(selection)
                        } else {
                            selectedToppings = selectedToppings.minusElement(selection)
                        }
                        Log.d("Toppings selected", "$selectedToppings")


                    }
                }

            }

        }
        FloatingActionButton(onClick = { onConfirm(pizzaId, selectedToppings) },
            modifier = Modifier
                .align(Alignment.BottomEnd).padding(20.dp),
            shape = MaterialTheme.shapes.extraLarge,
            containerColor = MaterialTheme.colorScheme.error

        ) {
            Icon(imageVector = Icons.Default.Done, contentDescription = "Done")


        }


    }


}

@Composable
fun ToppingItem(
    modifier: Modifier = Modifier,
    topping: Topping,
    onToppingSelected: (Topping) -> Unit,

    ) {
    var selectedCheckbox by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .size(80.dp)


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Checkbox(
                checked = selectedCheckbox,
                onCheckedChange = {
                    Log.d("Checkbox value", "$it")
                    selectedCheckbox = it
                    topping.isChecked = selectedCheckbox
                    onToppingSelected(topping)
                }

            )
            Text(
                text = topping.title,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.scrim

            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "${topping.price}",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.error
            )

        }
    }

}

//@Preview
//@Composable
//private fun ToppingItemPreview() {
//    ToppingItem(topping = Topping("Cheese",100.00))
//
//}

@Preview(showSystemUi = true)
@Composable
private fun ToppingSelectionScreenPreview() {
    ToppingSelectionScreen(pizzaId = 0) { _, _ -> }


}
