package uz.alphadroid.mycompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.alphadroid.mycompose.viewmodels.MainViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel()) {
    val itemsList = mainViewModel.itemsList.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = mainViewModel.newText.value, onValueChange = {
                    mainViewModel.newText.value = it
                },
                label = {
                    Text(text = "input description !")
                },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )
            )
            IconButton(onClick = {
                mainViewModel.insertItem()
            })
            {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(itemsList.value) { item ->
                ListItem(item, {
                    mainViewModel.nameEntity = it
                    mainViewModel.newText.value = it.name
                },
                    {
                        mainViewModel.deleteItem(it)
                    })
            }
        }
    }
}
