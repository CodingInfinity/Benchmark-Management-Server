namespace cpp com.codinginfinity.benchmark.management.thrift.messages
namespace java com.codinginfinity.benchmark.management.thrift.messages

enum MeasurementType {
	CPU,
	MEM,
	TIME
}

enum LanguageType {
	JAVA
}

struct JobSpecificationMessage {
	1: i32 experimentId;
	2: i32 jobId;
	3: LanguageType languageType;
	4: MeasurementType measurementType;
	5: binary dataset;
	6: binary algorithm;
	7: i32 timeout;
	8: i32 probeInterval;
}

struct ResultMessage {
	1: i32 experimentId;
	2: i32 jobId;
	3: list<Measurement> measurements;
}

struct Measurement {
	1: i32 timestamp;
	2: i32 value;
}

struct Heartbeat {
    1: string id;
    2: string description;
    3: string cpu;
    4: string memory;
    5: string os;
    6: string kernel;
    7: string name;
    8: string email;
    9: string phone;
    10: i64 current;
    11: i32 heartbeat;
    12: bool busy;
}