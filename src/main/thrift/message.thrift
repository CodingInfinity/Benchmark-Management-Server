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

struct JobSpecification {
	1: i32 experimentId;
	2: i32 jobId;
	3: LanguageType languageType;
	4: MeasurementType measurementType;
	3: binary dataset;
	4: binary algorithm;
}
