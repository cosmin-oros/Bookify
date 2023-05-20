package cosmin.dev.bookify.open_library_api

import cosmin.dev.bookify.R

object DefaultBooks {
    fun getBooks(): ArrayList<Book> {
        return arrayListOf(
            Book(
              title = "Atomic Habits",
              author = "James Clear",
              description = "No matter your goals, Atomic Habits offers a proven framework for improving—every day. James Clear, one of the world's leading experts on habit formation, reveals practical strategies that will teach you exactly how to form good habits, break bad ones, and master the tiny behaviors that lead to remarkable results.",
              imageUrl = R.drawable.atomichabits
            ),
            Book(
                title = "Man's Search for Meaning",
                author = "Viktor E. Frankl",
                description = "Psychiatrist Viktor Frankl's memoir has riveted generations of readers with its descriptions of life in Nazi death camps and its lessons for spiritual survival.",
                imageUrl = R.drawable.manssearchformeaning
            ),
            Book(
                title = "The Magic of Thinking Big",
                author = "David J. Schwartz",
                description = "The Magic of Thinking Big gives you useful methods, not empty promises. Dr. Schwartz presents a carefully designed program for getting the most out of your job, your marriage and family life, and your community.",
                imageUrl = R.drawable.themagicofthinkingbig
            ),
            Book(
                title = "Deep Work",
                author = "Cal Newport",
                description = "Deep work is the ability to focus without distraction on a cognitively demanding task. It's a skill that allows you to quickly master complicated information and produce better results in less time.",
                imageUrl = R.drawable.deepwork
            ),
            Book(
                title = "The Alchemist",
                author = "Paulo Coelho",
                description = "Combining magic, mysticism, wisdom, and wonder into an inspiring tale of self-discovery, The Alchemist has become a modern classic, selling millions of copies around the world and transforming the lives of countless readers across generations.",
                imageUrl = R.drawable.thealchemist
            ),
        )
    }
}