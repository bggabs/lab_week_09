package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_week_09.ui.theme.OnBackgroundTitleText
import com.example.lab_week_09.ui.theme.PrimaryTextButton
import com.example.lab_week_09.ui.theme.OnBackgroundItemText


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home()
                }
            }
        }
    }
}

// -----------------------------------------------------------------------------
// DATA MODEL
// -----------------------------------------------------------------------------
data class Student(
    var name: String
)

// -----------------------------------------------------------------------------
// PARENT COMPOSABLE — MENGELOLA STATE
// -----------------------------------------------------------------------------
@Composable
fun Home() {

    // State list (seperti useState list di React)
    val listData = remember {
        mutableStateListOf(
            Student("Tanu"),
            Student("Tina"),
            Student("Tono")
        )
    }

    // State input field (menyimpan nama yg sedang diketik)
    var inputField by remember {
        mutableStateOf(Student(""))
    }

    HomeContent(
        listData = listData,
        inputField = inputField,
        onInputValueChange = { newValue ->
            inputField = inputField.copy(name = newValue)
        },
        onButtonClick = {
            if (inputField.name.isNotBlank()) {
                listData.add(Student(inputField.name))
                inputField = Student("") // reset
            }
        }
    )
}

// -----------------------------------------------------------------------------
// CHILD COMPOSABLE — HANYA UNTUK UI
// -----------------------------------------------------------------------------
@Composable
fun HomeContent(
    listData: SnapshotStateList<Student>,
    inputField: Student,
    onInputValueChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    LazyColumn {

        item {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnBackgroundTitleText(
                    text = stringResource(id = R.string.enter_item)
                )

                TextField(
                    value = inputField.name,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = { onInputValueChange(it) }
                )

                PrimaryTextButton(
                    text = stringResource(id = R.string.button_click)
                ) {
                    onButtonClick()
                }
            }
        }

        items(listData) { item ->
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnBackgroundItemText(text = item.name)
            }
        }
    }

}

// -----------------------------------------------------------------------------
// PREVIEW (untuk Android Studio preview)
// -----------------------------------------------------------------------------
@Preview(showBackground = true)
@Composable
fun PreviewHomeContent() {
    MaterialTheme {
        HomeContent(
            listData = SnapshotStateList<Student>().apply {
                add(Student("Preview 1"))
                add(Student("Preview 2"))
            },
            inputField = Student(""),
            onInputValueChange = {},
            onButtonClick = {}
        )
    }
}
