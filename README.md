# Twitter Data Storage Replica

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MessagePack](https://img.shields.io/badge/MessagePack-8B4513?style=for-the-badge)
![Jackson](https://img.shields.io/badge/Jackson-3E7EBF?style=for-the-badge)


## Project Description

**Twitter Data Storage Replica** is a lightweight simulation of Twitter’s backend data architecture, implemented entirely in Java. It demonstrates how message storage, user interactions, and timeline generation can be managed without traditional databases.

This system uses a **custom mailbox-based storage design**, where each user has a dedicated data stream for posting and retrieving messages. It relies on **random-access file operations**, **binary serialization (MessagePack)**, and a fully **self-managed storage system** for high-speed access and schema evolution. It is built as a foundational experiment in building microblogging infrastructure without SQL or NoSQL dependencies.


## Features

- **User mailbox system** for independent message streams  
- **Random-access file storage** for fast direct data reads/writes  
- **Custom encoding with MessagePack** for compact binary storage  
- **Hot and cold tweet separation** for storage lifecycle management  
- **User follow system** for personalized timeline views  
- **Schema evolution** support (add, rename, and remove fields dynamically)  
- **Fully self-contained DB logic** without using external databases  


## Technologies Used

- **Java** — Main implementation language  
- **MessagePack** — Binary serialization for compact storage  
- **Jackson** — JSON mapping and schema evolution support  


## Getting Started

### Prerequisites

Make sure the following are installed:

- Java Development Kit (JDK)
- Git
- *(Optional)* Flutter SDK — required only if integrating with a frontend


### Installation and Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/darrellathaya/low-twitter-mailbox-randomaccess-encoding-owndb.git
   cd low-twitter-mailbox-randomaccess-encoding-owndb
   ```

2. **Download Required Dependencies**
   Create a `lib/` folder and fetch external libraries:
   ```bash
   mkdir lib && cd lib

   wget https://repo1.maven.org/maven2/org/msgpack/msgpack-core/0.9.8/msgpack-core-0.9.8.jar
   wget https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.15.3/jackson-databind-2.15.3.jar
   wget https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.15.3/jackson-core-2.15.3.jar
   wget https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.15.3/jackson-annotations-2.15.3.jar

   cd ..
   ```

3. **Compile Java Source Files**
   ```bash
   javac -cp ".:lib/*" *.java
   ```


## Usage Guide

### Post a New Message
```bash
java -cp ".:lib/*" Post <username> "<message>"
```

### Post with a Custom Timestamp
```bash
java -cp ".:lib/*" PostWithCustomDate <username> "<message>"
```

### Follow a User
```bash
java -cp ".:lib/*" Follow <follower_username> <followee_username>
```

### View a User’s Timeline
```bash
java -cp ".:lib/*" Timeline <username>
```

### Display All Stored Tweets (Hot + Cold)
```bash
java -cp ".:lib/*" ShowAllTweets
```

### Search Tweets by Date Range
```bash
java -cp ".:lib/*" SearchTweetsByDate <start_date> <end_date>
```


## Schema Evolution

The system supports live schema changes across all stored data. Use the following commands:

### Add a New Column
```bash
java -cp ".:lib/*" SchemaEvolver add <column_name> <default_value>
```

### Rename a Column
```bash
java -cp ".:lib/*" SchemaEvolver rename <old_column_name> <new_column_name>
```

### Remove a Column
```bash
java -cp ".:lib/*" SchemaEvolver remove <column_name>
```


## Project Structure

```
low-twitter-mailbox-randomaccess-encoding-owndb/
├── lib/                    # External .jar dependencies (MessagePack, Jackson)
├── users/                  # Mailbox and follower data
├── Post.java               # CLI for posting messages
├── PostWithCustomDate.java # Allows setting a custom timestamp
├── Follow.java             # Follow/unfollow logic
├── Timeline.java           # Timeline builder based on follow graph
├── ShowAllTweets.java      # Merges all tweet records for inspection
├── SearchTweetsByDate.java # Query tweets between date ranges
├── SchemaEvolver.java      # Handles adding/renaming/removing fields
```


## License

This project is licensed under the MIT License.
