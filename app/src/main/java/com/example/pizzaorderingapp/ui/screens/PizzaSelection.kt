package com.example.pizzaorderingapp.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzaorderingapp.Pizza
import com.example.pizzaorderingapp.PizzaType
import com.example.pizzaorderingapp.R
import com.example.pizzaorderingapp.dummyPizzaRepository

@Composable
fun PizzaSelectionScreen(
    modifier: Modifier = Modifier,
    onConfirm: (Int) -> Unit,// code hoisting
) {
    var selectedPizza by remember { mutableStateOf<Pizza?>(null) }
    var selectedPizzaIdx by remember { mutableIntStateOf(-1) }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.scrim)
    )  {

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
                ) {
            itemsIndexed(dummyPizzaRepository()){idx, it->
                PizzaItem(pizza = it) { item ->
                    selectedPizza = item
                    dummyPizzaRepository().indexOf(item).let{itemIdx ->
                        selectedPizzaIdx = itemIdx
                    }
                }
                  }
        }
        AnimatedVisibility(selectedPizza != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Selected Pizza: ${selectedPizza?.title}",
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { selectedPizza = null }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Done"
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { if(selectedPizzaIdx != -1){
                onConfirm(selectedPizzaIdx) } } ,
            modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp),
            shape = MaterialTheme.shapes.extraLarge,
            containerColor = MaterialTheme.colorScheme.error


        ) {
           androidx.compose.material3.Icon(imageVector = Icons.Default.Done, contentDescription = "Done")
        }
    }

}

//fun PizzaItem(pizza: Pizza, any: Any) {
//
//}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaItem(
              modifier: Modifier = Modifier,
              pizza: Pizza,
              onPizzaSelected: (Pizza) -> Unit = {},
              ) {
                  Card(
                      modifier = Modifier.fillMaxWidth(),
                      onClick = { onPizzaSelected(pizza)}
                       ){

                      Row(
                          modifier = Modifier.fillMaxWidth().padding(16.dp)
                          ) {

                              Image(
                                    painter = painterResource(id = pizza.image) ,
                                    contentDescription = pizza.title,
                                    modifier = Modifier.size(160.dp)
                                   )
                              Column(
                                     modifier
                                         .height(200.dp)
                                         .weight(1f)
                                         .padding(16.dp),
                                          verticalArrangement = Arrangement.Center,
                                     ){

                                     Text(
                                          text = pizza.title,
                                          style = MaterialTheme.typography.bodyLarge,
                                          fontSize = 26.sp,
                                          fontWeight = FontWeight.Bold,
                                         color = MaterialTheme.colorScheme.scrim,
                                          modifier = Modifier.basicMarquee()
                                         )
                                     Text(
                                          text = "${pizza.price}",
                                          fontWeight = FontWeight.Bold,
                                          fontSize = 24.sp,
                                          color = MaterialTheme.colorScheme.error
                                         )

                                     Box(
                                          modifier = Modifier
                                         .background(Color.Black)
                                         .padding(6.dp),
                                        ) {

                                     Text(
                                          text = pizza.pizzaType.name,
                                          fontWeight = FontWeight.Medium,
                                          fontSize = 18.sp,
                                          color = when (pizza.pizzaType) {
                                          PizzaType.VEG -> Color.Green.copy(alpha = .6f)
                                          PizzaType.NON_VEG -> Color.Red
                                          }
                                     )
                              }
                          }

                      }
                  }
    
              }

fun Text(text: String, fontWeight: FontWeight, fontSize: TextUnit, color: Any) {

}

//@Preview
//@Composable
//private fun PizzaItemPreview() {
//    PizzaItem(pizza = Pizza(
//        title = "Cheese Pizza",
//        image = R.drawable.cheese,
//        price = 299.0,
//        pizzaType = PizzaType.VEG
//        )
//    )
//
//}
@Preview
@Composable
private fun PizzaSelectionPreview() {
    PizzaSelectionScreen() {

    }
}