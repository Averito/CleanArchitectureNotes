package com.averito.mimi.ui.composables.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.averito.mimi.ui.screens.main.MainScreen
import com.averito.mimi.ui.screens.note.NoteScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                toNotePage = { noteId ->
                    navController.navigate("note/$noteId")
                }
            )
        }

        composable("note/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLongOrNull()
            NoteScreen(noteId = id, toBack = { navController.popBackStack() })
        }
    }
}
