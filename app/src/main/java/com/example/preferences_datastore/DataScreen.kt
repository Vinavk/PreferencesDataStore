package com.example.preferences_datastore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.emptyPreferences
import com.example.preferences_datastore.viewmodel.DataViewModel
@Composable
fun DataScreen(viewmodel: DataViewModel) {
    val preferencesFlow = viewmodel.getAllData().collectAsState(initial = emptyPreferences())

    val storedData = preferencesFlow.value

    Box(modifier = Modifier.padding(20.dp)) {
        LazyColumn {
            items(storedData.asMap().entries.toList()) { entry ->
                ItemData(key = entry.key.name, value = entry.value.toString()
                ) {

                        viewmodel.deletedata(entry.key.name)
                }
            }
        }
    }
}
@Composable
fun ItemData(key: String, value: String, ondelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Key: $key",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Value: $value",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            IconButton(onClick = {
                ondelete()

                 }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")

            }
        }
    }
}

