package com.samuelokello.roomdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.samuelokello.roomdemo.ui.theme.RoomDemoTheme

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(AppDatabase.getDatabaseInstance(this).userDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomDemoTheme {
                val users by userViewModel.users.collectAsState(initial = emptyList())

                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                userViewModel.addUser(User(firstName = "Samuel", lastName = "Okello"))
                            }
                        ) {
                            Text("Add User")
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        UsersListScreen(
                            users = users,
                            onClickDeleteUser = { user ->
                                userViewModel.deleteUser(user)
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersListScreen(
    users: List<User>,
    onClickDeleteUser: (user: User) -> Unit,
) {
    LazyColumn {
        items(
            items = users,
            key = {
                it.uId
            },
        ) { user ->
            Card(
                modifier = Modifier
                    .animateItemPlacement()
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    text = "Name: ${user.firstName} ${user.lastName}",
                )

                IconButton(onClick = {
                    onClickDeleteUser(user)
                }) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Delete,
                        tint = Color.Red,
                        contentDescription = "",
                    )
                }
            }
        }
    }
}
