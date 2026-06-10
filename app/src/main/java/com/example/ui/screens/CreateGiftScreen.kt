package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ui.LovedOneViewModel
import com.example.ui.components.FrostedBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGiftScreen(navController: NavController, viewModel: LovedOneViewModel) {
    var recipientName by remember { mutableStateOf("") }
    var occasion by remember { mutableStateOf("") }
    var additionalContext by remember { mutableStateOf("") }
    var selectedTheme by remember { mutableStateOf("Lavender Dream") }
    
    val generatedMessage = viewModel.generatedMessage
    val isGenerating = viewModel.isGenerating

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gift Creation Studio") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets.systemBars
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Step 1: Details", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            
            OutlinedTextField(
                value = recipientName,
                onValueChange = { recipientName = it },
                label = { Text("Recipient Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                )
            )
            
            OutlinedTextField(
                value = occasion,
                onValueChange = { occasion = it },
                label = { Text("Occasion (e.g. Birthday, Anniversary)") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                )
            )

            OutlinedTextField(
                value = additionalContext,
                onValueChange = { additionalContext = it },
                label = { Text("Special Details/Context for AI") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                minLines = 2,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                )
            )

            Button(
                onClick = {
                    if (recipientName.isNotBlank() && occasion.isNotBlank()) {
                        viewModel.generateMessage(recipientName, occasion, additionalContext) {}
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isGenerating && recipientName.isNotBlank() && occasion.isNotBlank()
            ) {
                Icon(Icons.Default.Star, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (isGenerating) "AI is crafting..." else "Create with AI Assistant")
            }

            if (generatedMessage.isNotBlank() || isGenerating) {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Step 2: Review Message", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                
                FrostedBox(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = generatedMessage.ifBlank { "..." },
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Step 3: Theme & Security", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                
                // Keep it simple for now
                OutlinedTextField(
                    value = selectedTheme,
                    onValueChange = { selectedTheme = it },
                    label = { Text("Design Theme") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                    )
                )

                Button(
                    onClick = {
                        viewModel.createGift(
                            type = occasion,
                            recipientName = recipientName,
                            message = generatedMessage,
                            theme = selectedTheme,
                            unlockType = "PIN",
                            unlockSecret = "1234",
                            onSuccess = { navController.popBackStack() }
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Save & Create Gift Link")
                }
            }
        }
    }
}
