# Microqueue

This project is aimed at creating a messaging queue system in C, designed to provide hands-on experience with various aspects of software development, networking, real-time OS concepts, and low-level programming in the Linux environment.

## Project Goals

- Develop a messaging queue system that supports basic publish, subscribe, and dequeue operations.
- Gain experience in network programming using custom network protocols.
- Explore real-time operating system concepts, such as threading and locking.
- Consider the optimization of performance-critical sections using assembly language.

## Features

- **Client-Server Architecture**: Implement a client-server model for message queuing.
- **Custom Network Protocol**: Develop a custom network protocol for efficient data exchange.
- **Message Queue Operations**: Support publish, subscribe, and dequeue operations.
- **Thread Management**: Manage multiple client connections concurrently using threads.

## Project Structure

The project structure is organized as follows:

- `src/`: Contains the source code for the messaging queue system.
- `prototype/`: Contains the source code for the working prototype, written in Java.
- `docs/`: Documentation and project-related files.
- `tests/`: Testing and validation code.
- `makefile`: Makefile for compiling the project.


## Getting Started

1. **Setup the Environment**: Ensure you have a Linux development environment with GCC, Makefiles, and shell scripting capabilities.

2. **Compilation**: Use the provided `makefile` to compile the source code.

3. **Testing**: Implement test cases in the `tests/` directory to verify the functionality.

4. **Documentation**: Generate documentation for your project to facilitate further development.

## Future Expansion

This messaging queue project can serve as a foundation for building a custom MQTT broker. MQTT is a more complex messaging protocol widely used in IoT applications. The experience gained from this project can be leveraged for the MQTT broker, which involves advanced protocol handling and more extensive feature development.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

## References
**Git**
* https://www.bitsnbites.eu/a-stable-mainline-branching-model-for-git/
* https://www.youtube.com/watch?v=Uszj_k0DGsg
* https://www.youtube.com/watch?v=ecK3EnyGD8o
* https://www.youtube.com/watch?v=qsTthZi23VE
**Networking**
* https://people.cs.rutgers.edu/~sn624/352-S21/syllabus.html
**Guides**
* https://github.com/practical-tutorials/project-based-learning
