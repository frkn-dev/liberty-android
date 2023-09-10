package org.fuckrkn1.android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import org.fuckrkn1.android.room.entity.CountryDB
import org.fuckrkn1.android.tunnel.CountryManager
import org.fuckrkn1.android.viewModels.CountrySpinnerViewModel

@Composable
fun CountrySpinner(
    viewModel: CountrySpinnerViewModel
){

    val countries by viewModel.countries.collectAsState()
    val lastSelectedCountry by viewModel.selectedCountry.collectAsState()

    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = lastSelectedCountry.name,
            onValueChange = {  },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = {
                Text("Country")
            },
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            countries.forEach { country ->
                DropdownMenuItem(onClick = {
                    viewModel.setupActiveCountryBy(country.name)
                    expanded = false
                }) {
                    Text(text = country.name)
                }
            }
        }
    }

    // API call
    LaunchedEffect(null) {
        viewModel.fetchCountries()
    }
}