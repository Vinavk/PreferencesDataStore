package com.example.preferences_datastore.themes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.preferences_datastore.viewmodel.DataViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(some: DataViewModel, navcontrol: NavHostController) {
    val keystate = remember { mutableStateOf("") }
    val valuestate = remember { mutableStateOf("") }
    val showstate = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Data Management",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )


        OutlinedTextField(
            value = keystate.value,
            onValueChange = { keystate.value = it },
            label = { Text("Key") },
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            value = valuestate.value,
            onValueChange = { valuestate.value = it },
            label = { Text("Value") },
            modifier = Modifier.fillMaxWidth()
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { some.savedata(keystate.value, valuestate.value) },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Save Data")
            }

            Button(
                onClick = {
                    some.viewModelScope.launch {
                        if (showstate.value.isNotEmpty()) {
                            showstate.value = some.showdata(showstate.value)
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Show Data")
            }
        }


        OutlinedTextField(
            value = showstate.value,
            onValueChange = { showstate.value = it },
            label = { Text("Displayed Data") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { navcontrol.navigate("DataScreen") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Show All Data")
        }
    }
}
