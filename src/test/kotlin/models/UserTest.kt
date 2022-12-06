package models

import controllers.UserAPI
import models.Post
import models.User
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
class UserTest {

    private var schoolTrip: Post? = null
    private var dinnerDate: Post? = null
    private var joeSoap: User? = null
    private var beyonceKnowles: User? = null
    private var workEvent: Post? = null
    private var travelPhoto: Post? = null
    private var natureWalk: Post? = null

    @BeforeEach
    fun setup(){
        joeSoap = User(1, "Joe Soap", "joeySoap", true, true, 74, 100, false, mutableSetOf<Post>())
        beyonceKnowles = User(5, "Beyonce Knowles", "Beyonce", true, true, 23, 3000, true, mutableSetOf<Post>())
        schoolTrip = Post(1, "School", " Went on a school trip today!", 100, 20)
        dinnerDate = Post(2, "Event", "Had a great time!", 300, 50)
        workEvent  = Post(3, "Work", "We'll be launching the product in November", 7000, 500)
        travelPhoto = Post(4, "Travel", "Visited 100 countries this year!", 700, 300)
        natureWalk = Post(5, "Adventure", "At one with nature", 30, 10)

        joeSoap!!.addPost(schoolTrip!!)
        joeSoap!!.addPost(dinnerDate!!)
        joeSoap!!.addPost(workEvent!!)
        joeSoap!!.addPost(travelPhoto!!)
        joeSoap!!.addPost(natureWalk!!)
    }

    @AfterEach
    fun tearDown(){
        schoolTrip = null
        dinnerDate = null
        workEvent = null
        travelPhoto = null
        natureWalk = null
        beyonceKnowles = null
        joeSoap = null
    }

    @Nested
    inner class AddPosts {
        @Test
        fun `adding a Post to a populated list adds to the array list`(){
            val newPost = Post(6, "Event", "Anna Delvey at charity event", 1000, 40)
            assertEquals(5, joeSoap!!.numberOfPosts())
            assertTrue(joeSoap!!.addPost(newPost))
            assertEquals(6, joeSoap!!.numberOfPosts())
            assertEquals(newPost, joeSoap!!.findOne(joeSoap!!.numberOfPosts() -1))
        }

        @Test
        fun `adding a post to an empty list adds to ArrayList`() {
            val newPost =  Post(6, "Event", "Anna Delvey at charity event", 1000, 40)
            assertEquals(0, beyonceKnowles!!.numberOfPosts())
            assertTrue(beyonceKnowles!!.addPost(newPost))
            assertEquals(1, beyonceKnowles!!.numberOfPosts())
            assertEquals(newPost, beyonceKnowles!!.findOne(beyonceKnowles!!.numberOfPosts() - 1))
        }
    }

    @Test
    fun `listAllPosts returns NO Recent Posts on the App message when ArrayList is empty`() {
        assertEquals(0, beyonceKnowles!!.numberOfPosts())
        assertTrue(beyonceKnowles!!.listAllPosts().lowercase().contains("no recent posts"))
    }

    @Test
    fun `listAllPosts returns Posts when ArrayList has users stored`() {
        assertEquals(5, joeSoap!!.numberOfPosts())
        val usersString = joeSoap!!.listAllPosts().lowercase()
        assertTrue(usersString.contains("school"))
        assertTrue(usersString.contains("event"))
        assertTrue(usersString.contains("work"))
        assertTrue(usersString.contains("travel"))
        assertTrue(usersString.contains("adventure"))
    }
    @Nested
    inner class DeleteUsers {
        @Test
        fun `deleting a Post that does not exist, returns null`() {
            assertFalse(beyonceKnowles!!.deletePost(0))
            assertFalse(joeSoap!!.deletePost(-1))
            assertFalse(joeSoap!!.deletePost(6))
        }

        @Test
        fun `deleting a Post that exists deletes the users account by`() {
            assertEquals(5, joeSoap!!.numberOfPosts())
            assertTrue( joeSoap!!.deletePost(4))
            assertEquals(4, joeSoap!!.numberOfPosts())
            assertTrue( joeSoap!!.deletePost(1))
            assertEquals(3, joeSoap!!.numberOfPosts())
        }
    }

    @Nested
    inner class UpdateUsers {
        @Test
        fun `updating a Post that does not exist returns false`() {
            assertFalse(joeSoap!!.updatePost(6, Post(6, "Website Crash", "Website is down for a few hours", 60, 400)))
            assertFalse(joeSoap!!.updatePost(-1, Post(-1, "Kotlin Course", "Kotlin Course in September", 200,200)))
            assertFalse(beyonceKnowles!!.updatePost(0, Post(0, "Award Show", "Jemmy won best actress!", 700,60)))
        }

        @Test
        fun `updating a Post that exists returns true and updates`() {
            //check user 1 exists.
            assertEquals(schoolTrip, joeSoap!!.findOne(0))
            assertEquals("School", joeSoap!!.findOne(0)!!.photoName)
            assertEquals(" Went on a school trip today!", joeSoap!!.findOne(0)!!.caption)

            //update note 5 with new information and ensure contents updated successfully
            assertTrue(joeSoap!!.updatePost(0, Post(1, "Event", "Was a great trip", 20,13)))
            assertEquals("Event", joeSoap!!.findOne(0)!!.photoName)
            assertEquals("Was a great trip", joeSoap!!.findOne(0)!!.caption)
        }
    }
}