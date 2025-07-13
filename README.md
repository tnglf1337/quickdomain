# Introduction

'Quickdomain' is a library where you can create fake domains of your domain object. 
This way you don't need to manually create fake objects for test cases or other usages.

# How to use
You will need an Api-Key from one of the supported GPT provider: 
- OpenAI   (<a href="https://platform.openai.com/api-keys">get one here</a>)

For example you have a domain called <code>User.class</code>
```
class User {
    String username;
    String email;
    
    public User() {}
    
    public User(String username, String email) {...}
    
    // class methods
}
```
## Generate a domain with a gpt provider
Just create a <code>SimpleDomain</code> Object and you will get a List<User> with generated domain entities:
```
String myApiKey = "sk-1234567890abcdef1234567890abcdef";
int numberOfEntities = 2;
GptFactory gptFactory = new OpenAiFactory(); // The gpt provider you want to use
SimpleDomainBuilder<User> builder = new SimpleDomainBuilder<>(gptFactory);

List<User> dummyDomain = builder.of(User.class, numberOfEntities)
                            .andProvider(GptProvider.OPEN_AI)
                            .andKey(myApiKey).generate();

sysout: [User{username='george44', email='someusername1@gmail.com'}, User{username='hellworld99', email='someemail2@gmail.com'}]
```

## Generate a domain with a raw content string
If you don't want to use a gpt provider, you can also use a raw content string.
```
String domainContent = "username,user1,user2\nemail,user1@example.com,user2@example";
List<User> dummyDomain = builder.of(User.class, numberOfEntities).generate(domainContent);
```

## Generate a domain with csv files
Alternatively, you can also ask the gpt manually to generate a csv file with the domain content.
Put it in the resources folder of your project and use:
```
Path filePath = Path.of("src/main/resources/domain-content.csv");
List<User> dummyDomain = builder.of(User.class, numberOfEntities).generate(filePath);
```