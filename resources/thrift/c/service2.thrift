include "domain2.thrift"

namespace java com.c.inflow.services
namespace cpp com.c.inflow.services

service TransferService{
	string sendSchema(1:domain2.CSchema cSchema) throws(1: domain2.TSQLException err1),
	string sendData(1:domain2.CData cData) throws(1: domain2.TSQLException err1),
	i32 queryDataCount(1:string dbName,2:string sql) throws(1: domain2.TSQLException err1),
	string queryData(1:string dbName,2:string sql,3:i32 curPage,4:i32 rows) throws(1: domain2.TSQLException err1)
}