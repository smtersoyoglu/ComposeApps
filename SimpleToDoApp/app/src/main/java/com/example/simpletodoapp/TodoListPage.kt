package com.example.simpletodoapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpletodoapp.entity.Todo
import com.example.simpletodoapp.ui.theme.WavePainter
import com.example.simpletodoapp.viewmodel.TodoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

// Composable fonksiyon: TodoListPage, ViewModel'den alınan yapılacaklar listesini görüntüler.
@Composable
fun TodoListPage(viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember { mutableStateOf("") }

    // Dalgalı arka plan için custom bir painter
    val backgroundPainter = remember {
        WavePainter(
            color1 = Color(0xFFB3E5FC), // Light Blue
            color2 = Color(0xFFE1F5FE), // Lighter Blue
            amplitude = 50f,
            frequency = 1f
        )
    }

    // Dalgalı arka planı uyguluyoruz
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(listOf(Color.White, Color(0xFFE1F5FE))))
            .paint(painter = backgroundPainter)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            Text(
                text = "Todo List",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF0277BD), // Daha koyu mavi başlık rengi
                modifier = Modifier.padding(top = 48.dp)
            )

            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text(text = "Add a new task", color = Color.Gray) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    viewModel.addTodo(inputText)
                    inputText = ""
                }) {
                    Text(
                        text = "Add",
                        fontSize = 16.sp
                    )
                }
            }

            todoList?.let {
                LazyColumn(
                    content = {
                        itemsIndexed(it) { _, item ->
                            TodoItem(
                                item = item,
                                onDelete = { viewModel.deleteTodo(item.id) },
                                onEdit = { newTitle -> viewModel.updateTodoTitle(item.id, newTitle) }
                            )
                        }
                    }
                )
            } ?: Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "No items yet",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit, onEdit: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf(item.title) }

    if (showDialog) {
        newTitle = item.title
        AlertDialog(onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = {
                    onEdit(newTitle)
                    showDialog = false
                }) {
                    Text(text = "Update")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = "Cancel")
                }
            },
            text = {
                Column {
                    Text("Edit Task")
                    OutlinedTextField(value = newTitle, onValueChange = { newTitle = it })
                }
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(start = 4.dp, end = 4.dp, top = 12.dp, bottom = 8.dp)
            .border(2.dp, Color(0xFF0277BD), RoundedCornerShape(16.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("tr", "TR")).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.Gray, // Tarih rengi gri
                modifier = Modifier
                    .padding(start = 12.dp) // Alt boşluk
            )

            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.Black, // Başlık rengi siyah
                modifier = Modifier.clickable { showDialog = true }
                    .padding(12.dp) // Text'in etrafına 16 dp boşluk ekleme

            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Delete",
                tint = Color.Red // Silme ikonunu kırmızı yapma
            )
        }
    }
}


