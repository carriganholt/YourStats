package com.carriganholt.yourstattracker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fieldgoal = findViewById<TextView>(R.id.fieldgoal)
        val freethrow = findViewById<TextView>(R.id.freethrow)
        val threepoint = findViewById<TextView>(R.id.threepoint)
        val points = findViewById<TextView>(R.id.points)
        val rebounds = findViewById<TextView>(R.id.rebounds)
        val assists = findViewById<TextView>(R.id.assists)
        val steals = findViewById<TextView>(R.id.steals)
        val blocks = findViewById<TextView>(R.id.blocks)
        val startGame = findViewById<Button>(R.id.startGame)
        val buttonLogOut = findViewById<Button>(R.id.buttonLogOut)

        var totalGames: Double
        var totalShots: Double
        var totalShotsMade: Double
        var totalFreeThrows: Double
        var totalFreeThrowsMade: Double
        var totalThreePoints: Double
        var totalThreePointsMade: Double
        var totalPoints: Double
        var totalRebounds: Double
        var totalAssists: Double
        var totalSteals: Double
        var totalBlocks: Double

        val df1 = "%.3f"
        val df2 = "%.1f"

        if(intent.hasExtra("games")) {
            totalGames = intent.getDoubleExtra("games", 0.0)
            totalShots = intent.getDoubleExtra("shots", 0.0)
            totalShotsMade = intent.getDoubleExtra("shotsMade", 0.0)
            totalFreeThrows = intent.getDoubleExtra("freeThrows", 0.0)
            totalFreeThrowsMade = intent.getDoubleExtra("freeThrowsMade", 0.0)
            totalThreePoints = intent.getDoubleExtra("threePoints", 0.0)
            totalThreePointsMade = intent.getDoubleExtra("threePointsMade", 0.0)
            totalPoints = intent.getDoubleExtra("points", 0.0)
            totalRebounds = intent.getDoubleExtra("rebounds", 0.0)
            totalAssists = intent.getDoubleExtra("assists", 0.0)
            totalSteals = intent.getDoubleExtra("steals", 0.0)
            totalBlocks = intent.getDoubleExtra("blocks", 0.0)

            if (totalGames == 0.0) {
                fieldgoal.text = "FG%  -  0.000"
                freethrow.text = "FT%  -  0.000"
                threepoint.text = "3P%  -  0.000"
                points.text = "PTS  -  0.0"
                rebounds.text = "REB  -  0.0"
                assists.text = "AST  -  0.0"
                steals.text = "STL  -  0.0"
                blocks.text = "BLK  -  0.0"

            } else if (totalShots == 0.0 && totalFreeThrows == 0.0 && totalThreePoints == 0.0) {
                fieldgoal.text = "FG%  -  0.000"
                freethrow.text = "FT%  -  0.000"
                threepoint.text = "3P%  -  0.000"
                points.text = "PTS  -  0.0"
                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

            } else if (totalShots == 0.0 && totalThreePoints == 0.0) {
                fieldgoal.text = "FG%  -  0.000"
                freethrow.text = "FT%  -  " + df1.format((totalFreeThrowsMade / totalFreeThrows))
                threepoint.text = "3P%  -  0.000"
                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

            } else if (totalShots == 0.0 && totalFreeThrows == 0.0) {
                fieldgoal.text = "FG%  -  0.000"
                freethrow.text = "FT%  -  0.000"
                threepoint.text = "3P%  -  " + df1.format((totalThreePointsMade / totalThreePoints))
                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

            } else if (totalThreePoints == 0.0 && totalFreeThrows == 0.0) {
                fieldgoal.text = "FG%  -  " + df1.format((totalShotsMade / totalShots))
                freethrow.text = "FT%  -  0.000"
                threepoint.text = "3P%  -  0.000"
                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

            } else if (totalShots == 0.0) {
                fieldgoal.text = "FG%  -  0.000"
                freethrow.text = "FT%  -  " + df1.format((totalFreeThrowsMade / totalFreeThrows))
                threepoint.text = "3P%  -  " + df1.format((totalThreePointsMade / totalThreePoints))
                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

            } else if (totalThreePoints == 0.0) {
                fieldgoal.text = "FG%  -  " + df1.format((totalShotsMade / totalShots))
                freethrow.text = "FT%  -  " + df1.format((totalFreeThrowsMade / totalFreeThrows))
                threepoint.text = "3P%  -  0.000"
                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

            } else if (totalFreeThrows == 0.0) {
                fieldgoal.text = "FG%  -  " + df1.format((totalShotsMade / totalShots))
                freethrow.text = "FT%  -  0.000"
                threepoint.text = "3P%  -  " + df1.format((totalThreePointsMade / totalThreePoints))
                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

            } else {
                fieldgoal.text = "FG%  -  " + df1.format((totalShotsMade / totalShots))
                freethrow.text = "FT%  -  " + df1.format((totalFreeThrowsMade / totalFreeThrows))
                threepoint.text = "3P%  -  " + df1.format((totalThreePointsMade / totalThreePoints))
                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))
            }

        } else {

            val db = FirebaseFirestore.getInstance()
            db.collection("stats").get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (i in task.result!!) {
                        if (i["id"].toString() == auth.currentUser!!.uid) {
                            totalGames = i["games"].toString().toDouble()
                            totalShots = i["shots"].toString().toDouble()
                            totalShotsMade = i["shotsMade"].toString().toDouble()
                            totalFreeThrows = i["freeThrows"].toString().toDouble()
                            totalFreeThrowsMade = i["freeThrowsMade"].toString().toDouble()
                            totalThreePoints = i["threePoints"].toString().toDouble()
                            totalThreePointsMade = i["threePointsMade"].toString().toDouble()
                            totalPoints = i["points"].toString().toDouble()
                            totalRebounds = i["rebounds"].toString().toDouble()
                            totalAssists = i["assists"].toString().toDouble()
                            totalSteals = i["steals"].toString().toDouble()
                            totalBlocks = i["blocks"].toString().toDouble()

                            if (totalGames == 0.0) {
                                fieldgoal.text = "FG%  -  0.000"
                                freethrow.text = "FT%  -  0.000"
                                threepoint.text = "3P%  -  0.000"
                                points.text = "PTS  -  0.0"
                                rebounds.text = "REB  -  0.0"
                                assists.text = "AST  -  0.0"
                                steals.text = "STL  -  0.0"
                                blocks.text = "BLK  -  0.0"

                            } else if (totalShots == 0.0 && totalFreeThrows == 0.0 && totalThreePoints == 0.0) {
                                fieldgoal.text = "FG%  -  0.000"
                                freethrow.text = "FT%  -  0.000"
                                threepoint.text = "3P%  -  0.000"
                                points.text = "PTS  -  0.0"
                                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

                            } else if (totalShots == 0.0 && totalThreePoints == 0.0) {
                                fieldgoal.text = "FG%  -  0.000"
                                freethrow.text = "FT%  -  " + df1.format((totalFreeThrowsMade / totalFreeThrows))
                                threepoint.text = "3P%  -  0.000"
                                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

                            } else if (totalShots == 0.0 && totalFreeThrows == 0.0) {
                                fieldgoal.text = "FG%  -  0.000"
                                freethrow.text = "FT%  -  0.000"
                                threepoint.text = "3P%  -  " + df1.format((totalThreePointsMade / totalThreePoints))
                                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

                            } else if (totalThreePoints == 0.0 && totalFreeThrows == 0.0) {
                                fieldgoal.text = "FG%  -  " + df1.format((totalShotsMade / totalShots))
                                freethrow.text = "FT%  -  0.000"
                                threepoint.text = "3P%  -  0.000"
                                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

                            } else if (totalShots == 0.0) {
                                fieldgoal.text = "FG%  -  0.000"
                                freethrow.text = "FT%  -  " + df1.format((totalFreeThrowsMade / totalFreeThrows))
                                threepoint.text = "3P%  -  " + df1.format((totalThreePointsMade / totalThreePoints))
                                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

                            } else if (totalThreePoints == 0.0) {
                                fieldgoal.text = "FG%  -  " + df1.format((totalShotsMade / totalShots))
                                freethrow.text = "FT%  -  " + df1.format((totalFreeThrowsMade / totalFreeThrows))
                                threepoint.text = "3P%  -  0.000"
                                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

                            } else if (totalFreeThrows == 0.0) {
                                fieldgoal.text = "FG%  -  " + df1.format((totalShotsMade / totalShots))
                                freethrow.text = "FT%  -  0.000"
                                threepoint.text = "3P%  -  " + df1.format((totalThreePointsMade / totalThreePoints))
                                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))

                            } else {
                                fieldgoal.text = "FG%  -  " + df1.format((totalShotsMade / totalShots))
                                freethrow.text = "FT%  -  " + df1.format((totalFreeThrowsMade / totalFreeThrows))
                                threepoint.text = "3P%  -  " + df1.format((totalThreePointsMade / totalThreePoints))
                                points.text = "PTS  -  " + df2.format((totalPoints / totalGames))
                                rebounds.text = "REB  -  " + df2.format((totalRebounds / totalGames))
                                assists.text = "AST  -  " + df2.format((totalAssists / totalGames))
                                steals.text = "STL  -  " + df2.format((totalSteals / totalGames))
                                blocks.text = "BLK  -  " + df2.format((totalBlocks / totalGames))
                            }
                        }
                    }
                }
            }
        }

        startGame.setOnClickListener {

            AlertDialog.Builder(this)
                    .setTitle("Start Game")
                    .setMessage("Are you sure you want to start a game?")
                    .setPositiveButton("Yes") { _, _ ->
                        startActivity(Intent(this, GameActivity::class.java))
                    }
                    .setNegativeButton("Cancel", null).show()
        }

        buttonLogOut.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("Log Out")
                    .setMessage("Are you sure you want to log out?")
                    .setPositiveButton("Yes") { _, _ ->
                        auth.signOut()
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                    .setNegativeButton("Cancel", null).show()
        }
    }

    override fun onBackPressed() {}
}