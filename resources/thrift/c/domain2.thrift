namespace java com.c.inflow.domain
namespace cpp com.c.inflow.services
typedef i16 int16
typedef i32 int32
typedef i64 int64

struct CSchema{
	1:string dbName,
	2:string tbName,
	3:string tbSchema
}
struct CData{
	1:string dbName,
	2:string tbName,
	3:string tbParCol,
	4:string tbParColVal,
	5:binary tbData,
	6:i16 progress
}

exception TSQLException {
  1: i32 errorCode,
  2: string msg
}