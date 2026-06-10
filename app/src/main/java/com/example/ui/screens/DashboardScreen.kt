package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.data.GiftEntity
import com.example.ui.LovedOneViewModel
import com.example.ui.components.FrostedBox
import com.example.ui.theme.PrimaryPink
import com.example.ui.theme.PrimaryPurple
import com.example.ui.theme.TextBody
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextTitle
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController, viewModel: LovedOneViewModel) {
    val gifts by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets.systemBars,
        bottomBar = {
            FrostedBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                backgroundColor = Color(0xCCFFFFFF)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomNavIcon(Icons.Default.Home, "Home", true)
                    BottomNavIcon(Icons.Outlined.Inventory2, "Studio", false)
                    
                    // Center Add Button
                    Box(modifier = Modifier.offset(y = (-20).dp)) {
                        FloatingActionButton(
                            onClick = { navController.navigate("create_gift") },
                            containerColor = Color.Transparent,
                            contentColor = Color.White,
                            shape = CircleShape,
                            modifier = Modifier
                                .size(56.dp)
                                .background(
                                    brush = Brush.linearGradient(listOf(PrimaryPurple, PrimaryPink)),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Create", modifier = Modifier.size(32.dp))
                        }
                    }

                    BottomNavIcon(Icons.Default.Notifications, "Activity", false)
                    BottomNavIcon(Icons.Default.Person, "Profile", false, onClick = {
                        com.google.firebase.auth.FirebaseAuth.getInstance().signOut()
                        navController.navigate("login") {
                            popUpTo("dashboard") { inclusive = true }
                        }
                    })
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("LovedOne", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = TextTitle)
                        Text("DREAMGIFT STUDIO", fontSize = 10.sp, fontWeight = FontWeight.Medium, color = PrimaryPurple.copy(alpha = 0.7f), letterSpacing = 2.sp)
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        // User avatar placeholder
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(Brush.linearGradient(listOf(Color(0xFFC084FC), Color(0xFFF9A8D4))), RoundedCornerShape(16.dp))
                        )
                    }
                }
            }

            item {
                // Hero AI Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp))
                ) {
                    // Blurred background layer
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Brush.linearGradient(listOf(PrimaryPurple.copy(alpha = 0.2f), PrimaryPink.copy(alpha = 0.2f))))
                    )
                    FrostedBox(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(32.dp),
                        elevation = 0.dp
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(Color(0xE6FFFFFF), RoundedCornerShape(16.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = PrimaryPurple)
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFFF3E8FF), CircleShape)
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text("AI POWERED", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryPurple)
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Need the perfect words?", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = TextTitle)
                            Text("Our AI can draft a custom anniversary message in seconds.", fontSize = 14.sp, color = TextTitle.copy(alpha = 0.6f), modifier = Modifier.padding(top = 4.dp, bottom = 16.dp))
                            Button(
                                onClick = { navController.navigate("create_gift") },
                                modifier = Modifier.fillMaxWidth().height(52.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryPurple)
                            ) {
                                Text("Create with AI Assistant", fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }

            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    // Quick actions
                    QuickActionCard(
                        icon = Icons.Default.Add,
                        title = "New Gift",
                        iconBgColor = Color(0xFFEFF6FF),
                        iconColor = Color(0xFF3B82F6),
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("create_gift") }
                    )
                    QuickActionCard(
                        icon = Icons.Default.Favorite,
                        title = "Vault",
                        iconBgColor = Color(0xFFF0FDF4),
                        iconColor = Color(0xFF22C55E),
                        modifier = Modifier.weight(1f),
                        onClick = { /* Navigate to vault */ }
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Scheduled Surprises", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextBody)
                    Text("View All", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryPurple)
                }
            }

            if (gifts.isEmpty()) {
                item {
                    Text(
                        "No gifts created yet.",
                        fontSize = 14.sp,
                        color = TextMuted,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(32.dp)
                    )
                }
            } else {
                items(gifts) { gift ->
                    GiftDashboardItem(gift) {
                        navController.navigate("view_gift/${gift.id}")
                    }
                }
            }
            
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun QuickActionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    iconBgColor: Color,
    iconColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FrostedBox(
        modifier = modifier.clickable { onClick() }.height(110.dp),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = Color(0xB3FFFFFF)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(48.dp).background(iconBgColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextBody)
        }
    }
}

@Composable
fun BottomNavIcon(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier.background(PrimaryPurple.copy(alpha = 0.1f), RoundedCornerShape(12.dp)).padding(8.dp)
            ) {
                Icon(icon, contentDescription = label, tint = PrimaryPurple)
            }
        } else {
            Icon(icon, contentDescription = label, tint = TextMuted, modifier = Modifier.padding(8.dp))
        }
        Text(
            label.uppercase(),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) PrimaryPurple else TextMuted,
            letterSpacing = (-0.5).sp
        )
    }
}

@Composable
fun GiftDashboardItem(gift: GiftEntity, onClick: () -> Unit) {
    FrostedBox(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFFFEDD5), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color(0xFFF97316),
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "For ${gift.recipientName}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextBody
                )
                Text(
                    text = "${gift.type} • ${SimpleDateFormat("MMM dd", Locale.getDefault()).format(Date(gift.createdAt))}",
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }
            Box(
                modifier = Modifier.background(PrimaryPurple.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
            ) {
                Text(
                    text = "Active",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryPurple,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        }
    }
}
