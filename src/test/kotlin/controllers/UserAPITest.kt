package controllers

import models.User
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue
import persistance.XMLSerializer
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class UserAPITest {

    private var joeSoap: User? = null
    private var lillyWalsh: User? = null
    private var jakeNichols: User? = null
    private var amalaDlamini: User? = null
    private var beyonceKnowles: User? = null
    private var populatedUsers: UserAPI? = UserAPI(XMLSerializer(File("users.xml")))
    private var emptyUsers: UserAPI? = UserAPI(XMLSerializer(File("users.xml")))

    @BeforeEach
    fun setup() {
        joeSoap = User(1, "Joe Soap", "joeySoap", true, true, 74, 100, false)
        lillyWalsh = User(2, "Lilly Walsh", "lilwalshx", true, true, 300, 470, false)
        jakeNichols = User(3, "Jake Nichols", "jakey", false, false, 400, 100, false)
        amalaDlamini = User(4, "Amala Dlamini", "Doja Cat", false, true, 50, 1000, true)
        beyonceKnowles = User(5, "Beyonce Knowles", "Beyonce", true, true, 23, 3000, true)

        populatedUsers!!.addUser(joeSoap!!)
        populatedUsers!!.addUser(lillyWalsh!!)
        populatedUsers!!.addUser(jakeNichols!!)
        populatedUsers!!.addUser(amalaDlamini!!)
        populatedUsers!!.addUser(beyonceKnowles!!)
    }

    @AfterEach
    fun tearDown() {
        joeSoap = null
        lillyWalsh = null
        jakeNichols = null
        amalaDlamini = null
        beyonceKnowles = null
        populatedUsers = null
        emptyUsers = null
    }

    @Nested
    inner class AddUsers {
        @Test
        fun `adding a User to a populated list adds to the array list`() {
            val newUser = User(6, "Anna Sorokin", "Anna Delvey", true, false, 50, 7000, false)
            assertEquals(5, populatedUsers!!.numberOfUsers())
            assertTrue(populatedUsers!!.addUser(newUser))
            assertEquals(6, populatedUsers!!.numberOfUsers())
            assertEquals(newUser, populatedUsers!!.findUser(populatedUsers!!.numberOfUsers() - 1))
        }

        @Test
        fun `adding a User to an empty list adds to ArrayList`() {
            val newUser = User(6, "Anna Sorokin", "Anna Delvey", true, false, 50, 7000, false)
            assertEquals(0, emptyUsers!!.numberOfUsers())
            assertTrue(emptyUsers!!.addUser(newUser))
            assertEquals(1, emptyUsers!!.numberOfUsers())
            assertEquals(newUser, emptyUsers!!.findUser(emptyUsers!!.numberOfUsers() - 1))
        }
    }

    @Nested
    inner class CountingMethods {

        @Test
        fun numberOfUsersCalculatedCorrectly() {
            assertEquals(5, populatedUsers!!.numberOfUsers())
            assertEquals(0, emptyUsers!!.numberOfUsers())
        }

        @Test
        fun numberOfActiveUsersCalculatedCorrectly() {
            assertEquals(3, populatedUsers!!.numberOfActiveUsers())
            assertEquals(0, emptyUsers!!.numberOfActiveUsers())
        }

        @Test
        fun numberOfOfflineUsersCalculatedCorrectly() {
            assertEquals(2, populatedUsers!!.numberOfUsersOffline())
            assertEquals(0, emptyUsers!!.numberOfUsersOffline())
        }

        @Test
        fun numberOfUsersWithProfilePictureCalculatedCorrectly() {
            assertEquals(4, populatedUsers!!.numberOfUsersWithProfilePic())
            assertEquals(0, emptyUsers!!.numberOfUsersWithProfilePic())
        }

        @Test
        fun numberOfUsersWithoutProfilePictureCalculatedCorrectly() {
            assertEquals(1, populatedUsers!!.numberOfUsersWithoutProfilePic())
            assertEquals(0, emptyUsers!!.numberOfUsersWithoutProfilePic())
        }

        @Test
        fun numberOfVerifiedUsersCalculatedCorrectly() {
            assertEquals(2, populatedUsers!!.numberOfVerifiedUsers())
            assertEquals(0, emptyUsers!!.numberOfVerifiedUsers())
        }

        @Test
        fun numberOfUnverifiedUsersCalculatedCorrectly() {
            assertEquals(3, populatedUsers!!.numberOfUnverifiedUsers())
            assertEquals(0, emptyUsers!!.numberOfUnverifiedUsers())
        }
    }

    @Nested
    inner class ListUsers {

        @Test
        fun `listAllUsers returns No Users on the App message when ArrayList is empty`() {
            assertEquals(0, emptyUsers!!.numberOfUsers())
            assertTrue(emptyUsers!!.listAllUsers().lowercase().contains("no users on the app"))
        }

        @Test
        fun `listAllUsers returns Users when ArrayList has users stored`() {
            assertEquals(5, populatedUsers!!.numberOfUsers())
            val usersString = populatedUsers!!.listAllUsers().lowercase()
            assertTrue(usersString.contains("joe soap"))
            assertTrue(usersString.contains("lilly walsh"))
            assertTrue(usersString.contains("jake nichols"))
            assertTrue(usersString.contains("amala dlamini"))
            assertTrue(usersString.contains("beyonce knowles"))
        }

        @Test
        fun `listActiveUsers returns no users online when ArrayList is empty`() {
            assertEquals(0, emptyUsers!!.numberOfActiveUsers())
            assertTrue(
                emptyUsers!!.listActiveUsers().lowercase().contains("no users online")
            )
        }

        @Test
        fun `listActiveUsers returns active users when ArrayList has active users stored`() {
            assertEquals(3, populatedUsers!!.numberOfActiveUsers())
            val activeUsersString = populatedUsers!!.listActiveUsers().lowercase()
            assertTrue(activeUsersString.contains("joe soap"))
            Assertions.assertFalse(activeUsersString.contains("amala dlamini"))
            assertTrue(activeUsersString.contains("lilly walsh"))
            assertTrue(activeUsersString.contains("beyonce knowles"))
            Assertions.assertFalse(activeUsersString.contains("jake nichols"))
        }

        @Test
        fun `listOfflineUsers returns no users when ArrayList is empty`() {
            assertEquals(0, emptyUsers!!.numberOfUsersOffline())
            assertTrue(
                emptyUsers!!.listOfflineUsers().lowercase().contains("all users are online")
            )
        }

        @Test
        fun `listOfflineUsers returns offline users when ArrayList has offline users stored`() {
            assertEquals(2, populatedUsers!!.numberOfUsersOffline())
            val offlineUsersString = populatedUsers!!.listOfflineUsers().lowercase()
            assertTrue(offlineUsersString.contains("jake nichols"))
            Assertions.assertFalse(offlineUsersString.contains("lilly walsh"))
            assertTrue(offlineUsersString.contains("amala dlamini"))
            Assertions.assertFalse(offlineUsersString.contains("beyonce knowles"))
        }

        @Test
        fun `listUsersWithProfilePic returns no users when ArrayList is empty`() {
            assertEquals(0, emptyUsers!!.numberOfUsersWithProfilePic())
            assertTrue(
                emptyUsers!!.listUsersWithProfilePic().lowercase().contains("no users have a profile pic")
            )
        }

        @Test
        fun `listUsersWithProfilePic returns users without a profile picture when ArrayList has users without a profile picture stored`() {
            assertEquals(4, populatedUsers!!.numberOfUsersWithProfilePic())
            val UsersWithProfilePicString = populatedUsers!!.listUsersWithProfilePic().lowercase()
            assertTrue(UsersWithProfilePicString.contains("joe soap"))
            Assertions.assertFalse(UsersWithProfilePicString.contains("jake nichols"))
            assertTrue(UsersWithProfilePicString.contains("lilly walsh"))
            assertTrue(UsersWithProfilePicString.contains("amala dlamini"))
            assertTrue(UsersWithProfilePicString.contains("beyonce knowles"))
        }

        @Test
        fun `listUsersWithoutProfilePic returns no users when ArrayList is empty`() {
            assertEquals(0, emptyUsers!!.numberOfUsersWithoutProfilePic())
            assertTrue(
                emptyUsers!!.listUsersWithoutProfilePic().lowercase().contains("all users have a profile pic")
            )
        }

        @Test
        fun `listUsersWithoutProfilePic returns users without a profile picture when ArrayList has users without a profile picture stored`() {
            assertEquals(1, populatedUsers!!.numberOfUsersWithoutProfilePic())
            val usersWithoutProfilePicString = populatedUsers!!.listUsersWithoutProfilePic().lowercase()
            assertTrue(usersWithoutProfilePicString.contains("jake nichols"))
            Assertions.assertFalse(usersWithoutProfilePicString.contains("amala dlamini"))
            Assertions.assertFalse(usersWithoutProfilePicString.contains("beyonce knowles"))
        }

        @Test
        fun `listVerifiedUsers returns no users when ArrayList is empty`() {
            assertEquals(0, emptyUsers!!.numberOfVerifiedUsers())
            assertTrue(
                emptyUsers!!.listVerifiedUsers().lowercase().contains("no users are verified")
            )
        }

        @Test
        fun `listVerifiedUsers returns verified users when ArrayList has unverified users stored`() {
            assertEquals(2, populatedUsers!!.numberOfVerifiedUsers())
            val verifiedUsersString = populatedUsers!!.listVerifiedUsers().lowercase()
            assertTrue(verifiedUsersString.contains("amala dlamini"))
            Assertions.assertFalse(verifiedUsersString.contains("joe soap"))
            assertTrue(verifiedUsersString.contains("beyonce knowles"))
            Assertions.assertFalse(verifiedUsersString.contains("lilly walsh"))
        }

        @Test
        fun `listUnverifiedUsers returns no users when ArrayList is empty`() {
            assertEquals(0, emptyUsers!!.numberOfUnverifiedUsers())
            assertTrue(
                emptyUsers!!.listUnverifiedUsers().lowercase().contains("all users are verified")
            )
        }

        @Test
        fun `listUnverifiedUsers returns unverified users when ArrayList has unverified users stored`() {
            assertEquals(3, populatedUsers!!.numberOfUnverifiedUsers())
            val unverifiedUsersString = populatedUsers!!.listUnverifiedUsers().lowercase()
            assertTrue(unverifiedUsersString.contains("joe soap"))
            Assertions.assertFalse(unverifiedUsersString.contains("amala dlamini"))
            assertTrue(unverifiedUsersString.contains("lilly walsh"))
            assertTrue(unverifiedUsersString.contains("jake nichols"))
            Assertions.assertFalse(unverifiedUsersString.contains("beyonce knowles"))
        }
    }

    /* @Test
        fun `listNotesBySelectedPriority returns No Notes when ArrayList is empty`() {
            assertEquals(0, emptyNotes!!.numberOfNotes())
            assertTrue(emptyNotes!!.listNotesBySelectedPriority(1).lowercase().contains("no notes")
            )
        }

        @Test
        fun `listNotesBySelectedPriority returns no notes when no notes of that priority exist`() {
            //Priority 1 (1 note), 2 (none), 3 (1 note). 4 (2 notes), 5 (1 note)
            assertEquals(5, populatedNotes!!.numberOfNotes())
            val priority2String = populatedNotes!!.listNotesBySelectedPriority(2).lowercase()
            assertTrue(priority2String.contains("no notes"))
            assertTrue(priority2String.contains("2"))
        }

        @Test
        fun `listNotesBySelectedPriority returns all notes that match that priority when notes of that priority exist`() {
            //Priority 1 (1 note), 2 (none), 3 (1 note). 4 (2 notes), 5 (1 note)
            assertEquals(5, populatedNotes!!.numberOfNotes())
            val priority1String = populatedNotes!!.listNotesBySelectedPriority(1).lowercase()
            assertTrue(priority1String.contains("1 note"))
            assertTrue(priority1String.contains("priority 1"))
            assertTrue(priority1String.contains("summer holiday"))
            Assertions.assertFalse(priority1String.contains("swim"))
            Assertions.assertFalse(priority1String.contains("learning kotlin"))
            Assertions.assertFalse(priority1String.contains("code app"))
            Assertions.assertFalse(priority1String.contains("test app"))

        }*/
    @Nested
    inner class DeleteUsers {
        @Test
        fun `deleting a User that does not exist, returns null`() {
            assertFalse(emptyUsers!!.deleteUser(0))
            assertFalse(populatedUsers!!.deleteUser(-1))
            assertFalse(populatedUsers!!.deleteUser(6))
        }

        @Test
        fun `deleting a User that exists deletes the users account by`() {
            assertEquals(5, populatedUsers!!.numberOfUsers())
            assertTrue(populatedUsers!!.deleteUser(4))
            assertEquals(4, populatedUsers!!.numberOfUsers())
            assertTrue(populatedUsers!!.deleteUser(1))
            assertEquals(3, populatedUsers!!.numberOfUsers())
        }
    }

    @Nested
    inner class UpdateUsers {
        @Test
        fun `updating a User that does not exist returns false`() {
            assertFalse(populatedUsers!!.updateUser(6, User(6, "Lani Good", "Laniii", true, true, 4000, 45000, true)))
            assertFalse(
                populatedUsers!!.updateUser(
                    -1,
                    User(-1, "KotlinLearner", "KotlinLearner47", false, false, 2000, 2000, false)
                )
            )
            assertFalse(emptyUsers!!.updateUser(0, User(0, "Jemima O", "Jemmy", false, true, 7000, 83000, true)))
        }

        @Test
        fun `updating a note that exists returns true and updates`() {
            //check user 1 exists.
            assertEquals(joeSoap, populatedUsers!!.findUser(0))
            assertEquals("Joe Soap", populatedUsers!!.findUser(0)!!.fullName)
            assertEquals("joeySoap", populatedUsers!!.findUser(0)!!.userName)

            //update note 5 with new information and ensure contents updated successfully
            assertTrue(populatedUsers!!.updateUser(0, User(0, "Joseph Soap", "JoSo", true, true, 74, 100, false)))
            assertEquals("Joseph Soap", populatedUsers!!.findUser(0)!!.fullName)
            assertEquals("JoSo", populatedUsers!!.findUser(0)!!.userName)
        }
    }

    @Nested
    inner class GoOnline {
        @Test
        fun `taking an account online that does not exist returns false`() {

            assertFalse(populatedUsers!!.goOnline(6))
            assertFalse(populatedUsers!!.goOnline(-1))
            assertFalse(populatedUsers!!.goOnline(0))
        }

        @Test
        fun `taking an account that exists online returns true and sets active now to true`() {
            assertEquals(jakeNichols, populatedUsers!!.findUser(2))
            assertEquals("Jake Nichols", populatedUsers!!.findUser(2)!!.fullName)
            assertEquals("jakey", populatedUsers!!.findUser(2)!!.userName)
            assertEquals(false, populatedUsers!!.findUser(2)!!.activeNow)
            assertEquals(false, populatedUsers!!.findUser(2)!!.profilePicture)
            assertEquals(400, populatedUsers!!.findUser(2)!!.following)
            assertEquals(100, populatedUsers!!.findUser(2)!!.followers)
            assertEquals(false, populatedUsers!!.findUser(2)!!.verified)
            assertTrue(populatedUsers!!.goOnline(2))
            assertEquals("Jake Nichols", populatedUsers!!.findUser(2)!!.fullName)
            assertEquals("jakey", populatedUsers!!.findUser(2)!!.userName)
            assertEquals(true, populatedUsers!!.findUser(2)!!.activeNow)
            assertEquals(false, populatedUsers!!.findUser(2)!!.profilePicture)
        }
    }

    @Nested
    inner class GoOffline {
        @Test
        fun `taking an account offline that does not exist returns false`() {

            assertFalse(populatedUsers!!.goOffline(6))
            assertFalse(populatedUsers!!.goOffline(-1))
            assertFalse(populatedUsers!!.goOffline(7))
        }

        @Test
        fun `taking an account that exists offline returns true and sets active now to false`() {
            assertEquals(lillyWalsh, populatedUsers!!.findUser(1))
            assertEquals("Lilly Walsh", populatedUsers!!.findUser(1)!!.fullName)
            assertEquals("lilwalshx", populatedUsers!!.findUser(1)!!.userName)
            assertEquals(true, populatedUsers!!.findUser(1)!!.activeNow)
            assertEquals(true, populatedUsers!!.findUser(1)!!.profilePicture)
            assertEquals(300, populatedUsers!!.findUser(1)!!.following)
            assertEquals(470, populatedUsers!!.findUser(1)!!.followers)
            assertEquals(false, populatedUsers!!.findUser(1)!!.verified)
            assertTrue(populatedUsers!!.goOffline(1))
            assertEquals("Lilly Walsh", populatedUsers!!.findUser(1)!!.fullName)
            assertEquals("lilwalshx", populatedUsers!!.findUser(1)!!.userName)
            assertEquals(false, populatedUsers!!.findUser(1)!!.activeNow)
            assertEquals(true, populatedUsers!!.findUser(1)!!.profilePicture)
        }
    }

    @Nested
    inner class GetVerified {
        @Test
        fun `verifying an account that does not exist returns false`() {

            assertFalse(populatedUsers!!.goOffline(6))
            assertFalse(populatedUsers!!.goOffline(-1))
            assertFalse(populatedUsers!!.goOffline(7))
        }

        @Test
        fun `verifying an account that exists returns true and sets active now to false`() {
            assertEquals(lillyWalsh, populatedUsers!!.findUser(1))
            assertEquals("Lilly Walsh", populatedUsers!!.findUser(1)!!.fullName)
            assertEquals("lilwalshx", populatedUsers!!.findUser(1)!!.userName)
            assertEquals(true, populatedUsers!!.findUser(1)!!.activeNow)
            assertEquals(true, populatedUsers!!.findUser(1)!!.profilePicture)
            assertEquals(300, populatedUsers!!.findUser(1)!!.following)
            assertEquals(470, populatedUsers!!.findUser(1)!!.followers)
            assertEquals(false, populatedUsers!!.findUser(1)!!.verified)
            assertTrue(populatedUsers!!.getVerified(1))
            assertEquals("Lilly Walsh", populatedUsers!!.findUser(1)!!.fullName)
            assertEquals("lilwalshx", populatedUsers!!.findUser(1)!!.userName)
            assertEquals(true, populatedUsers!!.findUser(1)!!.activeNow)
            assertEquals(true, populatedUsers!!.findUser(1)!!.profilePicture)
            assertEquals(300, populatedUsers!!.findUser(1)!!.following)
            assertEquals(470, populatedUsers!!.findUser(1)!!.followers)
            assertEquals(true, populatedUsers!!.findUser(1)!!.verified)
        }
    }

    @Nested
    inner class GetUnverified {
        @Test
        fun `unverifying an account that does not exist returns false`() {

            assertFalse(populatedUsers!!.unverifyAccount(6))
            assertFalse(populatedUsers!!.unverifyAccount(-1))
            assertFalse(populatedUsers!!.unverifyAccount(7))
        }

        @Test
        fun `unverifying that exists returns true and sets active now to false`() {
            assertEquals(beyonceKnowles, populatedUsers!!.findUser(4))
            assertEquals("Beyonce Knowles", populatedUsers!!.findUser(4)!!.fullName)
            assertEquals("Beyonce", populatedUsers!!.findUser(4)!!.userName)
            assertEquals(true, populatedUsers!!.findUser(4)!!.activeNow)
            assertEquals(true, populatedUsers!!.findUser(4)!!.profilePicture)
            assertEquals(23, populatedUsers!!.findUser(4)!!.following)
            assertEquals(3000, populatedUsers!!.findUser(4)!!.followers)
            assertEquals(true, populatedUsers!!.findUser(4)!!.verified)
            assertTrue(populatedUsers!!.unverifyAccount(4))
            assertEquals("Beyonce Knowles", populatedUsers!!.findUser(4)!!.fullName)
            assertEquals("Beyonce", populatedUsers!!.findUser(4)!!.userName)
            assertEquals(true, populatedUsers!!.findUser(4)!!.activeNow)
            assertEquals(true, populatedUsers!!.findUser(4)!!.profilePicture)
            assertEquals(23, populatedUsers!!.findUser(4)!!.following)
            assertEquals(3000, populatedUsers!!.findUser(4)!!.followers)
            assertEquals(false, populatedUsers!!.findUser(4)!!.verified)
        }
    }

    @Nested
    inner class SetProfilePic {
        @Test
        fun `setting a profile pic on an account that does not exist returns false`() {

            assertFalse(populatedUsers!!.profilePic(6))
            assertFalse(populatedUsers!!.profilePic(-1))
            assertFalse(populatedUsers!!.profilePic(7))
        }

        @Test
        fun `setting a profile pic on an account that exists returns true and sets active now to false`() {
            assertEquals(jakeNichols, populatedUsers!!.findUser(2))
            assertEquals("Jake Nichols", populatedUsers!!.findUser(2)!!.fullName)
            assertEquals("jakey", populatedUsers!!.findUser(2)!!.userName)
            assertEquals(false, populatedUsers!!.findUser(2)!!.activeNow)
            assertEquals(false, populatedUsers!!.findUser(2)!!.profilePicture)
            assertEquals(400, populatedUsers!!.findUser(2)!!.following)
            assertEquals(100, populatedUsers!!.findUser(2)!!.followers)
            assertEquals(false, populatedUsers!!.findUser(2)!!.verified)
            assertTrue(populatedUsers!!.profilePic(2))
            assertEquals("Jake Nichols", populatedUsers!!.findUser(2)!!.fullName)
            assertEquals("jakey", populatedUsers!!.findUser(2)!!.userName)
            assertEquals(false, populatedUsers!!.findUser(2)!!.activeNow)
            assertEquals(true, populatedUsers!!.findUser(2)!!.profilePicture)
        }
    }

        @Nested
        inner class RemoveProfilePic {
            @Test
            fun `removing a profile pic on an account that does not exist returns false`() {

                assertFalse(populatedUsers!!.noProfilePic(6))
                assertFalse(populatedUsers!!.noProfilePic(-1))
                assertFalse(populatedUsers!!.noProfilePic(7))
            }

            @Test
            fun `removing a profile pic on an account that exists returns true and sets active now to false`() {
                assertEquals(beyonceKnowles, populatedUsers!!.findUser(4))
                assertEquals("Beyonce Knowles", populatedUsers!!.findUser(4)!!.fullName)
                assertEquals("Beyonce", populatedUsers!!.findUser(4)!!.userName)
                assertEquals(true, populatedUsers!!.findUser(4)!!.activeNow)
                assertEquals(true, populatedUsers!!.findUser(4)!!.profilePicture)
                assertEquals(23, populatedUsers!!.findUser(4)!!.following)
                assertEquals(3000, populatedUsers!!.findUser(4)!!.followers)
                assertEquals(true, populatedUsers!!.findUser(4)!!.verified)
                assertTrue(populatedUsers!!.noProfilePic(4))
                assertEquals("Beyonce Knowles", populatedUsers!!.findUser(4)!!.fullName)
                assertEquals("Beyonce", populatedUsers!!.findUser(4)!!.userName)
                assertEquals(true, populatedUsers!!.findUser(4)!!.activeNow)
                assertEquals(false, populatedUsers!!.findUser(4)!!.profilePicture)
                assertEquals(23, populatedUsers!!.findUser(4)!!.following)
                assertEquals(3000, populatedUsers!!.findUser(4)!!.followers)
                assertEquals(true, populatedUsers!!.findUser(4)!!.verified)
            }
        }

            @Nested
            inner class SearchMethods {

                @Test
                fun `search users by name returns no users when no users with that name exists`() {
                    assertEquals(5, populatedUsers!!.numberOfUsers())
                    val searchResults = populatedUsers!!.searchByFullName("no results expected")
                    assertTrue(searchResults.isEmpty())

                    assertEquals(0, emptyUsers!!.numberOfUsers())
                    assertTrue(emptyUsers!!.searchByFullName("no results expected").isEmpty())
                }

                @Test
                fun `search users by name returns users when users with that name exist`() {
                    assertEquals(5, populatedUsers!!.numberOfUsers())

                    var searchResults = populatedUsers!!.searchByFullName("Joe Soap")
                    assertTrue(searchResults.contains("Joe Soap"))
                    assertFalse(searchResults.contains("Amala Dlamini"))

                    searchResults = populatedUsers!!.searchByFullName("Soap")
                    assertTrue(searchResults.contains("Joe Soap"))
                    assertFalse(searchResults.contains("Amala Dlamini"))

                    searchResults = populatedUsers!!.searchByFullName(("soAp"))
                    assertTrue(searchResults.contains("Joe Soap"))
                    assertFalse(searchResults.contains("Amala Dlamini"))
                }

                @Test
                fun `search users by username returns no users when no users with that username exists`() {
                    assertEquals(5, populatedUsers!!.numberOfUsers())
                    val searchResults = populatedUsers!!.searchByUsername("no results expected")
                    assertTrue(searchResults.isEmpty())

                    assertEquals(0, emptyUsers!!.numberOfUsers())
                    assertTrue(emptyUsers!!.searchByUsername("no results expected").isEmpty())
                }

                @Test
                fun `search user by username returns notes when notes with that title exist`() {
                    assertEquals(5, populatedUsers!!.numberOfUsers())

                    var searchResults = populatedUsers!!.searchByUsername("joeySoap")
                    assertTrue(searchResults.contains("joeySoap"))
                    assertFalse(searchResults.contains("Doja Cat"))

                    searchResults = populatedUsers!!.searchByUsername("Soap")
                    assertTrue(searchResults.contains("joeySoap"))
                    assertFalse(searchResults.contains("Doja Cat"))

                    searchResults = populatedUsers!!.searchByUsername(("soAp"))
                    assertTrue(searchResults.contains("joeySoap"))
                    assertFalse(searchResults.contains("Doja Cat"))
                }

            }

            @Nested
            inner class PersistenceTests {

                @Test
                fun `saving and loading an empty collection in XML doesn't crash app`() {
                    // Saving an empty users.XML file.
                    val storingUsers = UserAPI(XMLSerializer(File("users.xml")))
                    storingUsers.store()

                    //Loading the empty users.xml file into a new object
                    val loadedUsers = UserAPI(XMLSerializer(File("users.xml")))
                    loadedUsers.load()

                    //Comparing the source of the users (storingUsers) with the XML loaded users (loadedUsers)
                    assertEquals(0, storingUsers.numberOfUsers())
                    assertEquals(0, loadedUsers.numberOfUsers())
                    assertEquals(storingUsers.numberOfUsers(), loadedUsers.numberOfUsers())
                }
            }

                @Test
                fun `saving and loading an loaded collection in XML doesn't loose data`() {
                    // Storing 3 users to the users.XML file.
                    val storingUsers = UserAPI(XMLSerializer(File("users.xml")))
                    storingUsers.addUser(joeSoap!!)
                    storingUsers.addUser(lillyWalsh!!)
                    storingUsers.addUser(jakeNichols!!)
                    storingUsers.store()

                    //Loading users.xml into a different collection
                    val loadedUsers = UserAPI(XMLSerializer(File("users.xml")))
                    loadedUsers.load()


                    // EDIT THE FIND USER FUNCTION

                    //Comparing the source of the users (storingUsers) with the XML loaded users (loadedUsers)
                    assertEquals(3, storingUsers.numberOfUsers())
                    assertEquals(3, loadedUsers.numberOfUsers())
                    assertEquals(storingUsers.numberOfUsers(), loadedUsers.numberOfUsers())
                    assertEquals(storingUsers.findUser(0), loadedUsers.findUser(0))
                    assertEquals(storingUsers.findUser(1), loadedUsers.findUser(1))
                    assertEquals(storingUsers.findUser(2), loadedUsers.findUser(2))
                }

            }
